package moviefinder.samusko.com.moviefinder.base.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by bohdan on 30.03.17.
 */

public class MovieThumbnailView extends ImageView {

    private final float HEIGTH_RATIO = 1.5f;

    public MovieThumbnailView(Context context) {
        super(context);
    }

    public MovieThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resultHeight = (int) (widthMeasureSpec * HEIGTH_RATIO);

        super.onMeasure(widthMeasureSpec, resultHeight);
    }
}
