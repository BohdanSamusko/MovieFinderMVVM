package moviefinder.samusko.com.moviefinder.ui.main;

import android.view.View;

/**
 * Created by bohdan on 30.03.17.
 */

public interface MainActivityContract {

    void findMovie(String query);
    void loadNextPage();
    void onFindMovieClick(View view);

}
