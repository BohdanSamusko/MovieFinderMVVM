package moviefinder.samusko.com.androidmoviefindermvvm.services.rest;


import moviefinder.samusko.com.androidmoviefindermvvm.services.rest.dto.SearchMovieListResponseDTO;
import retrofit2.http.GET;

public interface RestApi {

    @GET("/search/movie")
    public SearchMovieListResponseDTO searchMovieByName();
}
