package moviefinder.samusko.com.moviefinder.services.rest.dto.movies;

import java.util.ArrayList;

import moviefinder.samusko.com.moviefinder.MovieFinderApp;
import moviefinder.samusko.com.moviefinder.R;

/**
 * Created by bohdan on 30.03.17.
 */

public class MovieDTO {

    private long id;
    private String original_title;
    private String original_language;
    private String title;

    private String backdrop_path;
    private float popularity;
    private long vote_count;
    private boolean video;
    private float vote_average;

    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private ArrayList<Integer> genre_ids;

    public long getId() {
        return id;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        String baseImageUrl = MovieFinderApp.getInstance().getString(R.string.base_image_url);
        if (backdrop_path!= null && !backdrop_path.startsWith(baseImageUrl)){
            backdrop_path = baseImageUrl + backdrop_path;
        }
        return backdrop_path;
    }

    public float getPopularity() {
        return popularity;
    }

    public long getVoteCount() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public String getPosterPath() {
        String baseImageUrl = MovieFinderApp.getInstance().getString(R.string.base_image_url);
        if (poster_path!= null && !poster_path.startsWith(baseImageUrl)){
            poster_path = baseImageUrl + poster_path;
        }

        return poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public ArrayList<Integer> getGenreIds() {
        return genre_ids;
    }
}
