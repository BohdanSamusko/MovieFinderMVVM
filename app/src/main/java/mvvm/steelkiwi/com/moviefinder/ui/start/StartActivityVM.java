package mvvm.steelkiwi.com.moviefinder.ui.start;

import android.content.Intent;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import mvvm.steelkiwi.com.moviefinder.base.mvvm.activities.ActivityViewModel;
import mvvm.steelkiwi.com.moviefinder.ui.main.MainActivity;
import mvvm.steelkiwi.com.moviefinder.utils.SimpleAnimListener;
import timber.log.Timber;

/**
 * Created by bohdan on 07.05.17.
 */

public class StartActivityVM extends ActivityViewModel<StartActivity> {


    public StartActivityVM(StartActivity activity) {
        super(activity);
    }

    private void openMainScreen() {
        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        finish();
    }


    public void onStartButtonClick(View view){
        handleOnStartButtonClick(view);
    }

    private void handleOnStartButtonClick(View view) {
        Timber.i("On start button click");

        int touchX = (int) (view.getX() + view.getWidth()/2);
        int touchY = (int) (view.getY() + view.getHeight()/2);

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
