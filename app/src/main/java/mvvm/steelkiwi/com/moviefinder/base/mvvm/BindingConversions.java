package mvvm.steelkiwi.com.moviefinder.base.mvvm;

import android.databinding.BindingConversion;
import android.view.View;


public final class BindingConversions {
    private BindingConversions() {
        throw new AssertionError(); // blocking creation of instance
    }

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

}
