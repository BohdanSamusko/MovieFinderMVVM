package mvvm.steelkiwi.com.moviefinder.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mvvm.steelkiwi.com.moviefinder.BR;
import mvvm.steelkiwi.com.moviefinder.R;
import mvvm.steelkiwi.com.moviefinder.base.BaseActivity;
import mvvm.steelkiwi.com.moviefinder.base.mvvm.activities.BindingActivity;
import mvvm.steelkiwi.com.moviefinder.databinding.ActivityStartBinding;
import mvvm.steelkiwi.com.moviefinder.ui.main.MainActivity;

public class StartActivity extends BindingActivity<ActivityStartBinding, StartActivityVM> {

    private StartActivityVM startActivityVM;

    @Override
    public StartActivityVM onCreate() {
        startActivityVM = new StartActivityVM(this);
        return startActivityVM;
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

}
