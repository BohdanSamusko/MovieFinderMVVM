package mvvm.steelkiwi.com.moviefinder.services.rest;


import mvvm.steelkiwi.com.moviefinder.services.rest.dto.movies.SearchMovieListResponseDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RestApi {

    @GET("/3/search/movie/")
    Observable<SearchMovieListResponseDTO> searchMovieByName(@Query("query") String query, @Query("page") String page, @Query("api_key") String apiKey);
}
