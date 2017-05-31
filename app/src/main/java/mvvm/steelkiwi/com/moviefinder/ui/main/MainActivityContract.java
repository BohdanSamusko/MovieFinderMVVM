package mvvm.steelkiwi.com.moviefinder.ui.main;

import android.view.View;

import mvvm.steelkiwi.com.moviefinder.services.rest.dto.movies.MovieDTO;

/**
 * Created by bohdan on 30.03.17.
 */

public interface MainActivityContract {

    void findMovie(String query);
    void loadNextPage();
    void onFindMovieClick(View view);

}
