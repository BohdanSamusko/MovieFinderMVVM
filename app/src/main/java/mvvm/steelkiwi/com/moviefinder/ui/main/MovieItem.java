package mvvm.steelkiwi.com.moviefinder.ui.main;

import android.databinding.ObservableBoolean;

import mvvm.steelkiwi.com.moviefinder.MovieFinderApp;
import mvvm.steelkiwi.com.moviefinder.R;
import mvvm.steelkiwi.com.moviefinder.services.rest.dto.movies.MovieDTO;
import mvvm.steelkiwi.com.moviefinder.utils.GlobalAppUtils;

/**
 * Created by bohdan on 27.05.17.
 */

public class MovieItem {
    public ObservableBoolean isShowDescription = new ObservableBoolean();

    private MovieDTO movieDTO;
    private String formattedReleaseDate = "";
    private String formattedOverview = "";

    public MovieItem(MovieDTO movieDTO) {
        this.movieDTO = movieDTO;
    }

    public MovieDTO getMovieDTO() {
        return movieDTO;
    }

    public String getFormattedReleaseDate() {
        return GlobalAppUtils.parseMovieReleaseDate(movieDTO.getReleaseDate());
    }

    public boolean getIsShowDescription() {
        return isShowDescription.get();
    }

    public void setIsShowDescription(boolean isShowDescription) {
        this.isShowDescription.set(isShowDescription);
    }

    public String getFormattedOverview() {
        if (movieDTO.getOverview().trim().equals("")){
            formattedOverview = MovieFinderApp.getInstance().getResources().getString(R.string.no_overview);
        }else {
            formattedOverview = movieDTO.getOverview();
        }
        return formattedOverview;
    }
}
