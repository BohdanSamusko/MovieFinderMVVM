package moviefinder.samusko.com.moviefinder.services.rest.dto.movies;

import java.util.ArrayList;

/**
 * Created by bohdan on 30.03.17.
 */

public class SearchMovieListResponseDTO {
    private String page;
    private ArrayList<MovieDTO> results;
    private long total_results;
    private long total_pages;


    public String getPage() {
        return page;
    }

    public ArrayList<MovieDTO> getResults() {
        return results;
    }

    public long getTotalResults() {
        return total_results;
    }

    public long getTotalPages() {
        return total_pages;
    }
}
