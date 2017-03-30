package moviefinder.samusko.com.androidmoviefindermvvm.ui.main;

import android.os.Bundle;

import moviefinder.samusko.com.androidmoviefindermvvm.BR;
import moviefinder.samusko.com.androidmoviefindermvvm.R;
import moviefinder.samusko.com.androidmoviefindermvvm.base.mvvm.activities.BindingActivity;
import moviefinder.samusko.com.androidmoviefindermvvm.databinding.ActivityMainBinding;

public class MainActivity extends BindingActivity<ActivityMainBinding, MainActivityViewModel> {

    private MainActivityViewModel mainActivityViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
