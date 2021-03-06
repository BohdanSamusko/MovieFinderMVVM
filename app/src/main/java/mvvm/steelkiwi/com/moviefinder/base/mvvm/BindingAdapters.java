package mvvm.steelkiwi.com.moviefinder.base.mvvm;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import mvvm.steelkiwi.com.moviefinder.R;


public final class BindingAdapters {

    private BindingAdapters() {
        throw new AssertionError(); // blocking creation of instance
    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        // loading image with third-party library or own custom utility
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

    @BindingAdapter("app:onClick")
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runnable.run();
            }
        });
    }
}
