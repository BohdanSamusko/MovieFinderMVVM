package moviefinder.samusko.com.androidmoviefindermvvm;

import android.app.Application;

/**
 * Created by bohdan on 30.03.17.
 */

public class MovieFinderApp extends Application {

    private static MovieFinderApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MovieFinderApp getInstance() {
        if (instance == null) {
            throw new RuntimeException("Application initialization error!");
        }
        return instance;
    }
}
