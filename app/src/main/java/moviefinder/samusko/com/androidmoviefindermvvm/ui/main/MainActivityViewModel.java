package moviefinder.samusko.com.androidmoviefindermvvm.ui.main;

import moviefinder.samusko.com.androidmoviefindermvvm.base.mvvm.activities.ActivityViewModel;

/**
 * Created by bohdan on 30.03.17.
 */

public class MainActivityViewModel extends ActivityViewModel<MainActivity> implements MainActivityContract{

    private MainActivity activity;

    public MainActivityViewModel(MainActivity activity) {
        super(activity);
        this.activity = activity;
    }


}
