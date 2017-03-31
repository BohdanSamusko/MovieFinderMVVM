package moviefinder.samusko.com.moviefinder.ui.movie_details;

import android.view.View;

import moviefinder.samusko.com.moviefinder.base.mvvm.activities.ActivityViewModel;
import moviefinder.samusko.com.moviefinder.services.rest.dto.movies.MovieDTO;

/**
 * Created by bohdan on 31.03.17.
 */

public class MovieDetailsViewModel extends ActivityViewModel<MovieDetailsActivity> implements MovieDetailsContract {
    private MovieDetailsActivity activity;

    private MovieDTO movieDTO;


    public MovieDetailsViewModel(MovieDetailsActivity activity) {
        super(activity);
        this.activity = activity;

        movieDTO = activity.getIntent().getParcelableExtra(MovieDetailsActivity.MOVIE_OBJECT);
    }


    public MovieDTO getMovieDTO() {
        return movieDTO;
    }


    @Override
    public void onBackIconClick(View view) {
        activity.onBackPressed();
    }

}
