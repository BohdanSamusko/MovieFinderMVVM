package mvvm.steelkiwi.com.moviefinder.ui.main;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import mvvm.steelkiwi.com.moviefinder.BR;
import mvvm.steelkiwi.com.moviefinder.R;
import mvvm.steelkiwi.com.moviefinder.base.mvvm.RecyclerBindingAdapter;
import mvvm.steelkiwi.com.moviefinder.base.mvvm.RecyclerConfiguration;
import mvvm.steelkiwi.com.moviefinder.base.mvvm.activities.ActivityViewModel;
import mvvm.steelkiwi.com.moviefinder.services.rest.dto.movies.MovieDTO;
import mvvm.steelkiwi.com.moviefinder.services.rest.dto.movies.SearchMovieListResponseDTO;
import mvvm.steelkiwi.com.moviefinder.services.rest.models.MoviesModel;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by bohdan on 30.03.17.
 */

public class MainActivityViewModel extends ActivityViewModel<MainActivity> implements MainActivityContract {

    private MainActivity activity;
    private MoviesModel moviesModel; // model for getting results by entered search word
    public final ObservableBoolean isLoading = new ObservableBoolean(); // boolean variable for changing visibility of progress bar view
    private final ObservableBoolean isLoadingNextPage = new ObservableBoolean(); // for showing or hiding container which marked that next page is loading
    public final ObservableField<String> enteredQuery = new ObservableField<>(); // query from editView
    private String searchedQuery; // query which is already searched

    public final RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration(); // for configuring recycler view

    private final int DEVICE_ITEM_LAYOUT = R.layout.item_movie_preview; // id of list item

    // collection for storing results of searching
    private ArrayList<MovieItem> moviesItems = new ArrayList<>();

    // custom binding adapter for recycler view
    private RecyclerBindingAdapter<MovieItem> adapter;

    // for pagination logic
    private int nextPage = 1;

    // clicked item
    private int clickedItemPosition = -1;

    public MainActivityViewModel(MainActivity activity) {
        super(activity);
        this.activity = activity;

        // init model for getting data
        moviesModel = new MoviesModel();

        initList();
    }

    private void initList() {
        adapter = getAdapter();

        // configuring recyclerConfiguration which is bound with recycler view
        recyclerConfiguration.setLayoutManager(new LinearLayoutManager(activity));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<MovieItem> getAdapter() {

        int variableId = BR.item; // variable which defined in layout file for movie item

        RecyclerBindingAdapter<MovieItem> moviesAdapter = new RecyclerBindingAdapter<>(DEVICE_ITEM_LAYOUT, variableId, moviesItems);

        // setting click listener for movie item
        moviesAdapter.setOnItemClickListener(new RecyclerBindingAdapter.OnItemClickListener<MovieItem>() {
            @Override
            public void onItemClick(int position, MovieItem item) {
                handleClickOnItem(position, item);
            }
        });

        // passing listener for pagination with atTheEndOfList() method which will invoke after user scroll all list to bottom
        moviesAdapter.setPaginationListener(new RecyclerBindingAdapter.PaginationListener<MovieItem>() {
            @Override
            public void atTheEndOfList(int position, MovieItem item) {
                loadNextPage();
            }
        });

        return moviesAdapter;
    }

    private void handleClickOnItem(int position, MovieItem item) {
        if (clickedItemPosition != -1 && clickedItemPosition != position) {
            MovieItem previouslyClickedItem = moviesItems.get(clickedItemPosition);
            if (previouslyClickedItem.getIsShowDescription()){
                previouslyClickedItem.setIsShowDescription(false);
                adapter.notifyItemChanged(clickedItemPosition);
            }
        }
        item.setIsShowDescription(!item.getIsShowDescription());
        adapter.notifyItemChanged(position);

        clickedItemPosition = position;
    }


    @Override
    public void onFindMovieClick(View view) {
        nextPage = 1;
        searchedQuery = enteredQuery.get();
        findMovie(searchedQuery);
        activity.hideSoftKeyboard(); // hide keyboard to enable displaying of all list with results
    }

    @Override
    public void findMovie(String query) {
        // checking if new page is available
        if (nextPage == -1) {
            Timber.i("All pages are loaded");
            return;
        }

        Timber.d("Starting loading movies for query: " + query);

        if (nextPage == 1) {
            isLoading.set(true); // show main progress bar
        } else {
            isLoadingNextPage.set(true); // show progress bar for displaying loading of new page
        }

        String apiKey = activity.getResources().getString(R.string.the_movie_db_api_key); // the api key for TheMovieDB API

        // making request with passing necessary parameters and subscribing to listen the results of request
        moviesModel.searchMovieByName(query, nextPage, apiKey).subscribe(new Subscriber<SearchMovieListResponseDTO>() {
            @Override
            public void onCompleted() {
                Timber.i("onCompleted");
                isLoading.set(false);
                isLoadingNextPage.set(false);
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("onError: " + e.getLocalizedMessage());
                Toast.makeText(activity, "onError:" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                isLoading.set(false);
                isLoadingNextPage.set(false);
            }

            @Override
            public void onNext(SearchMovieListResponseDTO searchMovieListResponseDTO) {
                Timber.i("onNext");
                isLoading.set(false);
                isLoadingNextPage.set(false);
                if (searchMovieListResponseDTO != null) {

                    // if we load first page it means search word was changed, so we need to remove previous results
                    if (nextPage == 1) {
                        moviesItems.clear();
                    }

                    for (MovieDTO movieDTO : searchMovieListResponseDTO.getResults()) {
                        moviesItems.add(new MovieItem(movieDTO));
                    }

                    adapter.notifyDataSetChanged();

                    if (nextPage < searchMovieListResponseDTO.getTotalPages()) {
                        nextPage++;
                    } else {
                        nextPage = -1; // mark that all pages loaded
                    }
                }
            }
        });
    }

    @Override
    public void loadNextPage() {
        if (!isLoading.get()) {
            findMovie(searchedQuery);
        }
    }

    public ArrayList<MovieItem> getMoviesItems() {
        return moviesItems;
    }

    public ObservableBoolean getIsLoadingNextPage() {
        return isLoadingNextPage;
    }

}
