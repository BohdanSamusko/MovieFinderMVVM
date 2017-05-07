package mvvm.steelkiwi.com.moviefinder.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import mvvm.steelkiwi.com.moviefinder.R;
import timber.log.Timber;

/**
 * Created by bohdan on 30.03.17.
 */

public class BaseActivity extends AppCompatActivity {

    private static final int TAP_BACK_TIME_INTERVAL = 1600;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        navigateBack();
    }

    private void navigateBack() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            Timber.i("Popping backstack");

            FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
            String fragmentName = backEntry.getName();
            fragmentManager.popBackStackImmediate(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            tryExitActivity();
        }
    }

    private void tryExitActivity() {
        Timber.i("Nothing on backstack, calling finish");

        this.hideSoftKeyboard();

        if (this.isTaskRoot()) {
            if (doubleBackToExitPressedOnce) {
                finish();
                return;
            }

            doubleBackToExitPressedOnce = true;
            showToast(getString(R.string.tap_twice_to_close_the_app));
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }

            }, TAP_BACK_TIME_INTERVAL);
        } else {
            finish();
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
