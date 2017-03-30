package moviefinder.samusko.com.androidmoviefindermvvm.services.rest;


import java.io.IOException;
import java.net.HttpURLConnection;

import moviefinder.samusko.com.androidmoviefindermvvm.MovieFinderApp;
import moviefinder.samusko.com.androidmoviefindermvvm.R;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;



public class RestService {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public RestService() {
    }

    public static RestApi createRdxtService() {

        Retrofit.Builder builder = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MovieFinderApp.getInstance().getApplicationContext().getString(R.string.base_url));

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newBuilder = request.newBuilder();

                Request newRequest = newBuilder
                        .build();

                Response response = null;
                try {
                    response = chain.proceed(newRequest);
                    switch (response.code()) {
                        case HttpURLConnection.HTTP_FORBIDDEN:
                            final String url = response.request().url().toString();
                            if (url != null) {

                            } else {
                                Timber.e("Response without request URL! " + response);
                            }
                            break;
                    }
                    ResponseBody body = response.body();
                    Timber.d("HTTP " + response.code() + " URL=" + response.request().url().toString());
                    String bodyString = body.string();
                    Timber.d(bodyString);


                    final Response.Builder newResponse = response.newBuilder()
                            .body(ResponseBody.create(JSON, bodyString));
                    response = newResponse.build();

                } catch (IOException e) {
                    e.getLocalizedMessage();

                }
                return response;
            }
        }).build();
        builder.client(client);

        return builder.build().create(RestApi.class);
    }
}
