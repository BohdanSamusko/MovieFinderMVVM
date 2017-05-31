package mvvm.steelkiwi.com.moviefinder.utils;

import android.os.Build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by bohdan on 27.05.17.
 */

public class GlobalAppUtils {

    public static String parseMovieReleaseDate(String releaseDate) {
        Timber.i("Release date : " + releaseDate);
        String parsedDate = "";

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = simpleDateFormat.parse(releaseDate);

            SimpleDateFormat output = new SimpleDateFormat("MMMM dd, yyyy");
            parsedDate = output.format(date);

        }catch (ParseException e){
            e.printStackTrace();
        }

        Timber.i("Parsed release date : " + parsedDate);
        return parsedDate;
    }

    public static boolean isLollipopOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
