package moviefinder.samusko.com.androidmoviefindermvvm.base.mvvm;

import android.databinding.BindingAdapter;
import android.view.View;


public final class BindingAdapters {
    private BindingAdapters() {
        throw new AssertionError();
    }

    /*@BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.bg_image_holder)
                .into(view);
    }*/

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
