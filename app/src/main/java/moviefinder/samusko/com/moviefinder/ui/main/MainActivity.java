package moviefinder.samusko.com.moviefinder.ui.main;

import android.os.Bundle;

import moviefinder.samusko.com.moviefinder.BR;
import moviefinder.samusko.com.moviefinder.R;
import moviefinder.samusko.com.moviefinder.base.mvvm.activities.BindingActivity;
import moviefinder.samusko.com.moviefinder.databinding.ActivityMainBinding;

public class MainActivity extends BindingActivity<ActivityMainBinding, MainActivityViewModel> {

    private MainActivityViewModel mainActivityViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public MainActivityViewModel onCreate() {
        mainActivityViewModel = new MainActivityViewModel(this);
        return mainActivityViewModel;
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
