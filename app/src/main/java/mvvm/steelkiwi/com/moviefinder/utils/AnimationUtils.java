package mvvm.steelkiwi.com.moviefinder.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

public class AnimationUtils {

    public static ObjectAnimator translateXAnim(View view, float from, float to, int duration, int delay, Interpolator interpolator) {
        ObjectAnimator transAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, from, to);
        transAnimation.setDuration(duration);//set duration
        transAnimation.setStartDelay(delay);
        if (interpolator != null) {
            transAnimation.setInterpolator(interpolator);
        }
        transAnimation.start();//start animation


        return transAnimation;
    }

    public static ObjectAnimator translateYAnim(View view, float from, float to, int duration, int delay, Interpolator interpolator) {
        ObjectAnimator transAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, from, to);
        transAnimation.setDuration(duration);//set duration
        transAnimation.setStartDelay(delay);
        if (interpolator != null) {
            transAnimation.setInterpolator(interpolator);
        }
        transAnimation.start();//start animation

        return transAnimation;
    }


    public static ObjectAnimator fadeAnimation(View view, float from, float to, int duration, int delay, Interpolator interpolator) {
        ObjectAnimator transAnimation = ObjectAnimator.ofFloat(view, "alpha", from, to);
        transAnimation.setDuration(duration);//set duration
        transAnimation.setStartDelay(delay);
        if (interpolator != null) {
            transAnimation.setInterpolator(interpolator);
        }
        transAnimation.start();//start animation

        return transAnimation;
    }


    public static Animator animateRevealColorFromCoordinates(Context context, final ViewGroup viewRoot, final int color, int x, int y, int delay, int duration, TimeInterpolator timeInterpolator) {
        if (!viewRoot.isAttachedToWindow()) {
            return null;
        }

        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        anim.setDuration(duration);
        anim.setStartDelay(delay);
        if (timeInterpolator!=null){
            anim.setInterpolator(timeInterpolator);
        }

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                viewRoot.setBackgroundColor(color);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
        return anim;
    }

    public static Animator animateCircularHidingColorFromCoordinates(Context context, ViewGroup viewRoot, int color, int x, int y, int duration) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, finalRadius, 0);
        viewRoot.setBackgroundColor(color);
        anim.setDuration(duration);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }


    public static HeightAnimation animateHeight(View view, int heightFrom, int heightTo, int duration, Interpolator interpolator) {
        HeightAnimation verticalAnimation = new HeightAnimation(view);
        verticalAnimation.setParams(heightFrom, heightTo);
        if (interpolator != null) {
            verticalAnimation.setInterpolator(interpolator);
        }
        verticalAnimation.setDuration(duration);
        view.startAnimation(verticalAnimation);

        return verticalAnimation;
    }

    public static ObjectAnimator zoomAnimation(View view, float from, float to, int duration, int delay, Interpolator interpolator) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", from, to);
        scaleX.setStartDelay(delay);
        scaleX.setDuration(duration);
        if (interpolator != null) {
            scaleX.setInterpolator(interpolator);
        }
        scaleX.start();

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", from, to);
        scaleY.setStartDelay(delay);
        scaleY.setDuration(duration);
        if (interpolator != null) {
            scaleY.setInterpolator(interpolator);
        }
        scaleY.start();

        return scaleX;
    }

    public static void scaleYAnim(View view, float from, float to, int duration, int delay, Interpolator interpolator) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", from, to);
        scaleY.setDuration(duration);
        if (interpolator != null) {
            scaleY.setInterpolator(interpolator);
        }
        scaleY.start();
    }

}