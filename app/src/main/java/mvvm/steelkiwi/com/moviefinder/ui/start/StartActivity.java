package mvvm.steelkiwi.com.moviefinder.ui.start;

import mvvm.steelkiwi.com.moviefinder.BR;
import mvvm.steelkiwi.com.moviefinder.R;
import mvvm.steelkiwi.com.moviefinder.base.mvvm.activities.BindingActivity;
import mvvm.steelkiwi.com.moviefinder.databinding.ActivityStartBinding;
import timber.log.Timber;

public class StartActivity extends BindingActivity<ActivityStartBinding, StartActivityVM> {

    private StartActivityVM startActivityVM;

    @Override
    public StartActivityVM onCreate() {
        Timber.i("onCreate();");
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
