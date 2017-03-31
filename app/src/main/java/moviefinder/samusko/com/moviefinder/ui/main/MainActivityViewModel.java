package moviefinder.samusko.com.moviefinder.ui.main;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import moviefinder.samusko.com.moviefinder.BR;
import moviefinder.samusko.com.moviefinder.R;
import moviefinder.samusko.com.moviefinder.base.mvvm.RecyclerBindingAdapter;
import moviefinder.samusko.com.moviefinder.base.mvvm.RecyclerConfiguration;
import moviefinder.samusko.com.moviefinder.base.mvvm.activities.ActivityViewModel;
import moviefinder.samusko.com.moviefinder.services.rest.dto.movies.MovieDTO;
import moviefinder.samusko.com.moviefinder.services.rest.dto.movies.SearchMovieListResponseDTO;
import moviefinder.samusko.com.moviefinder.services.rest.models.MoviesModel;
import moviefinder.samusko.com.moviefinder.ui.movie_details.MovieDetailsActivity;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by bohdan on 30.03.17.
 */

public class MainActivityViewModel extends ActivityViewModel<MainActivity> implements MainActivityContract {

    private MainActivity activity;
    private MoviesModel moviesModel;
    public final ObservableBoolean isLoading = new ObservableBoolean();
    private final ObservableBoolean isLoadingNextPage = new ObservableBoolean();
    public final ObservableField<String> enteredQuery = new ObservableField<>(); // query from editView
    private String searchedQuery; // query which is already searched

    public final RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration();

    private final int DEVICE_ITEM_LAYOUT = R.layout.item_movie_preview;

    private ArrayList<MovieDTO> moviesItems = new ArrayList<>();
    private RecyclerBindingAdapter<MovieDTO> adapter;

    // for pagination logic
    private int currentPage = 1;
    private int nextPage = 1;


    public MainActivityViewModel(MainActivity activity) {
        super(activity);
        this.activity = activity;

        moviesModel = new MoviesModel();

        initList();
    }

    private void initList() {
        adapter = getAdapter();

        recyclerConfiguration.setLayoutManager(new LinearLayoutManager(activity));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<MovieDTO> getAdapter() {
        RecyclerBindingAdapter<MovieDTO> moviesAdapter = new RecyclerBindingAdapter<>(DEVICE_ITEM_LAYOUT, BR.item, moviesItems);

        moviesAdapter.setOnItemClickListener(new RecyclerBindingAdapter.OnItemClickListener<MovieDTO>() {
            @Override
            public void onItemClick(int position, MovieDTO item) {
                openMovieDetails(item);
            }
        });

        moviesAdapter.setPaginationListener(new RecyclerBindingAdapter.PaginationListener<MovieDTO>() {
            @Override
            public void atTheEndOfList(int position, MovieDTO item) {
                loadNextPage();
            }
        });

        return moviesAdapter;
    }


    @Override
    public void onFindMovieClick(View view) {
        nextPage = 1;
        currentPage = 1;
        searchedQuery = enteredQuery.get();
        findMovie(searchedQuery);
        activity.hideSoftKeyboard(); // hide keyboard to enable displaying of all
    }

    @Override
    public void findMovie(String query) {
        if (currentPage != 1 && nextPage == currentPage) {
            Timber.i("All pages are loaded");
            return;
        }


        Timber.d("Starting loading movies for query: " + query);

        if (nextPage == 1) {
            isLoading.set(true);
        }else {
            isLoadingNextPage.set(true);
        }

        String apiKey = activity.getResources().getString(R.string.the_movie_db_api_key);
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
                    if (nextPage == 1) {
                        moviesItems.clear();
                    }
                    moviesItems.addAll(searchMovieListResponseDTO.getResults());
                    adapter.notifyDataSetChanged();

                    currentPage = Integer.parseInt(searchMovieListResponseDTO.getPage());
                    if (nextPage < searchMovieListResponseDTO.getTotalPages()) {
                        nextPage++;
                    }
                }
            }
        });
    }


    @Override
    public void loadNextPage() {
        findMovie(searchedQuery);
    }


    public ArrayList<MovieDTO> getMoviesItems() {
        return moviesItems;
    }

    public ObservableBoolean getIsLoadingNextPage() {
        return isLoadingNextPage;
    }

    @Override
    public void openMovieDetails(MovieDTO movieDTO) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.MOVIE_OBJECT, movieDTO);
        activity.startActivity(intent);
    }
}
