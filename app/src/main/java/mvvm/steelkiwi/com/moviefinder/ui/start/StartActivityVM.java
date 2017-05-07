package mvvm.steelkiwi.com.moviefinder.ui.start;

import android.content.Intent;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.graphics.Point;
import android.graphics.drawable.Animatable2;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import mvvm.steelkiwi.com.moviefinder.base.mvvm.activities.ActivityViewModel;
import mvvm.steelkiwi.com.moviefinder.databinding.ActivityStartBinding;
import mvvm.steelkiwi.com.moviefinder.ui.main.MainActivity;
import mvvm.steelkiwi.com.moviefinder.utils.SimpleAnimListener;
import timber.log.Timber;

/**
 * Created by bohdan on 07.05.17.
 */

public class StartActivityVM extends ActivityViewModel<StartActivity> {


    public StartActivityVM(StartActivity activity) {
        super(activity);
        Timber.i("StartActivityVM();");
        StartScreenAnimations.onScreenRevealAnimation(getActivity().getBinding());
    }

    private void openMainScreen() {
        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        finish();
    }


    public void onStartButtonClick(View view) {
        handleOnStartButtonClick(view);
    }

    private void handleOnStartButtonClick(View view) {
        Timber.i("On start button click");

        int touchX = (int) (view.getX() + view.getWidth() / 2);
        int touchY = (int) (view.getY() + view.getHeight() / 2);

        StartScreenAnimations.onStartButtonAnimation(getActivity().getBinding(), new Point(touchX, touchY), new SimpleAnimListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinished() {
                openMainScreen();
            }
        });
    }
}
