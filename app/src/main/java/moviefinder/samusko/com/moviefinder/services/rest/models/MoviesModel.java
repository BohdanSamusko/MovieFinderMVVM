package moviefinder.samusko.com.moviefinder.services.rest.models;

import moviefinder.samusko.com.moviefinder.services.rest.RestApi;
import moviefinder.samusko.com.moviefinder.services.rest.RestService;
import moviefinder.samusko.com.moviefinder.services.rest.RetryWithDelay;
import moviefinder.samusko.com.moviefinder.services.rest.dto.movies.SearchMovieListResponseDTO;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bohdan on 30.03.17.
 */

public class MoviesModel {
    private RestApi restApi;

    public MoviesModel() {
        this.restApi = RestService.createRdxtService();
    }

    public Observable<SearchMovieListResponseDTO> searchMovieByName(String query, int page, String apiKey) {
        return restApi.searchMovieByName(query, String.valueOf(page), apiKey)
                .retryWhen(new RetryWithDelay(3, 1000))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
