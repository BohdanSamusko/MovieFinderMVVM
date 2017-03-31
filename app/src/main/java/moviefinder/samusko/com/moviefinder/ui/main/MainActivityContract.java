package moviefinder.samusko.com.moviefinder.ui.main;

import android.view.View;

import moviefinder.samusko.com.moviefinder.services.rest.dto.movies.MovieDTO;

/**
 * Created by bohdan on 30.03.17.
 */

public interface MainActivityContract {

    void findMovie(String query);
    void loadNextPage();
    void onFindMovieClick(View view);

    void openMovieDetails(MovieDTO movieDTO);

}
