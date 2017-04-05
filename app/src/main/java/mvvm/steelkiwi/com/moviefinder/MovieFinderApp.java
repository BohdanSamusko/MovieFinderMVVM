package mvvm.steelkiwi.com.moviefinder;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by bohdan on 30.03.17.
 */

public class MovieFinderApp extends Application {

    private static MovieFinderApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
    }

    public static MovieFinderApp getInstance() {
        if (instance == null) {
            throw new RuntimeException("Application initialization error!");
        }
        return instance;
    }
}
