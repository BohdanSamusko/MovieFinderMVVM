package mvvm.steelkiwi.com.moviefinder.ui.main;

import android.os.Bundle;

import mvvm.steelkiwi.com.moviefinder.BR;
import mvvm.steelkiwi.com.moviefinder.R;
import mvvm.steelkiwi.com.moviefinder.base.mvvm.activities.BindingActivity;
import mvvm.steelkiwi.com.moviefinder.databinding.ActivityMainBinding;

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

    // method returns variable which defined in xml file
    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
