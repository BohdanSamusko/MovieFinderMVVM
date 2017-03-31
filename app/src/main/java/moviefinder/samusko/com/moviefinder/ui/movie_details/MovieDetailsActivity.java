package moviefinder.samusko.com.moviefinder.ui.movie_details;

import android.os.Bundle;

import moviefinder.samusko.com.moviefinder.BR;
import moviefinder.samusko.com.moviefinder.R;
import moviefinder.samusko.com.moviefinder.base.mvvm.activities.BindingActivity;
import moviefinder.samusko.com.moviefinder.databinding.ActivityMovieDetailsBinding;

public class MovieDetailsActivity extends BindingActivity<ActivityMovieDetailsBinding, MovieDetailsViewModel> {

    public static final String MOVIE_OBJECT = "movie_object";

    private MovieDetailsViewModel movieDetailsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public MovieDetailsViewModel onCreate() {
        movieDetailsViewModel = new MovieDetailsViewModel(this);
        return movieDetailsViewModel;
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_details;
    }
}
