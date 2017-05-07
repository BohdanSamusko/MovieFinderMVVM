package mvvm.steelkiwi.com.moviefinder.ui.start;

import android.animation.Animator;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import mvvm.steelkiwi.com.moviefinder.MovieFinderApp;
import mvvm.steelkiwi.com.moviefinder.R;
import mvvm.steelkiwi.com.moviefinder.databinding.ActivityStartBinding;
import mvvm.steelkiwi.com.moviefinder.utils.AnimationUtils;
import mvvm.steelkiwi.com.moviefinder.utils.SimpleAnimListener;

/**
 * Created by bohdan on 07.05.17.
 */

public class StartScreenAnimations {

    private final static int SCREEN_START_ELEMENTS_DURATION = 450;

    private final static int COLOR_REVEALING_DURATION = 500;
    private final static int ELEMENTS_FADING_DURATION = 350;
    private final static int ELEMENTS_TRANSLATE_VALUE = 200;


    public static void onScreenRevealAnimation(@NonNull final ActivityStartBinding binding){
        AnimationUtils.fadeAnimation(binding.projectTitle, 0f, 1f, SCREEN_START_ELEMENTS_DURATION, 0, new AccelerateInterpolator());
        AnimationUtils.translateYAnim(binding.projectTitle, -ELEMENTS_TRANSLATE_VALUE, 0, SCREEN_START_ELEMENTS_DURATION, 0, new AccelerateInterpolator());

        binding.projectDescription.setVisibility(View.INVISIBLE);
        AnimationUtils.fadeAnimation(binding.projectDescription, 0f, 1f, SCREEN_START_ELEMENTS_DURATION, SCREEN_START_ELEMENTS_DURATION, new AccelerateInterpolator()).addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                binding.projectDescription.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        binding.startButton.setVisibility(View.INVISIBLE);
        AnimationUtils.fadeAnimation(binding.startButton, 0f, 1f, SCREEN_START_ELEMENTS_DURATION, 2*SCREEN_START_ELEMENTS_DURATION, new AccelerateInterpolator()).addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                binding.startButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }


    public static void onStartButtonAnimation(@NonNull final ActivityStartBinding binding, @NonNull Point pointOfClick, @NonNull final SimpleAnimListener simpleAnimListener) {

        // animation for elements
        View title = binding.projectTitle;
        AnimationUtils.fadeAnimation(title, 1f, 0f, ELEMENTS_FADING_DURATION, 0, new AccelerateInterpolator());
        //AnimationUtils.translateYAnim(title, 0,  ELEMENTS_TRANSLATE_VALUE, ELEMENTS_FADING_DURATION, 0, new AccelerateInterpolator());
        AnimationUtils.translateXAnim(title, 0,  ELEMENTS_TRANSLATE_VALUE, ELEMENTS_FADING_DURATION, 0, new AccelerateInterpolator());

        View description = binding.projectDescription;
        AnimationUtils.fadeAnimation(description, 1f, 0f, ELEMENTS_FADING_DURATION, 0, new AccelerateInterpolator());
       // AnimationUtils.translateYAnim(description, 0,  ELEMENTS_TRANSLATE_VALUE, ELEMENTS_FADING_DURATION, 0, new AccelerateInterpolator());
        AnimationUtils.translateXAnim(description, 0,  -ELEMENTS_TRANSLATE_VALUE, ELEMENTS_FADING_DURATION, 0, new AccelerateInterpolator());


        View startButton = binding.startButton;
        AnimationUtils.fadeAnimation(startButton, 1f, 0f, ELEMENTS_FADING_DURATION, 0, new AccelerateInterpolator());
        AnimationUtils.zoomAnimation(startButton, 1f, 0f, ELEMENTS_FADING_DURATION, 0, new AccelerateInterpolator());

        // color revealing animation
        int revealColor = MovieFinderApp.getInstance().getResources().getColor(R.color.colorPrimary);

        Interpolator interpolator = new AccelerateInterpolator();

        ViewGroup topLayout = binding.topLayout;
        AnimationUtils.fadeAnimation(topLayout, 0f, 1f, COLOR_REVEALING_DURATION, ELEMENTS_FADING_DURATION, new AccelerateInterpolator());
        AnimationUtils.animateRevealColorFromCoordinates(MovieFinderApp.getInstance().getApplicationContext(), topLayout,
                revealColor, pointOfClick.x, pointOfClick.y, ELEMENTS_FADING_DURATION, COLOR_REVEALING_DURATION, interpolator).addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                simpleAnimListener.onStart();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                simpleAnimListener.onFinished();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
