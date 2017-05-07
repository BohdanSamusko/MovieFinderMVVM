package mvvm.steelkiwi.com.moviefinder.ui.splash;

import android.content.Intent;
import android.view.View;

import mvvm.steelkiwi.com.moviefinder.base.mvvm.activities.ActivityViewModel;
import mvvm.steelkiwi.com.moviefinder.ui.main.MainActivity;

/**
 * Created by bohdan on 07.05.17.
 */

public class StartActivityVM extends ActivityViewModel<StartActivity> {
    public StartActivityVM(StartActivity activity) {
        super(activity);
    }

    public void onStartClick(View view) {
        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        finish();
    }
}
