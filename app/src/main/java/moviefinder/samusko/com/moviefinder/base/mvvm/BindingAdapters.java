package moviefinder.samusko.com.moviefinder.base.mvvm;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import moviefinder.samusko.com.moviefinder.R;


public final class BindingAdapters {

    private BindingAdapters() {
        throw new AssertionError();
    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.ic_default_movie_thumbnail)
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
