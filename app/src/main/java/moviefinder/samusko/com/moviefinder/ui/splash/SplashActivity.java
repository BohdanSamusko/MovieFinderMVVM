package moviefinder.samusko.com.moviefinder.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import moviefinder.samusko.com.moviefinder.R;
import moviefinder.samusko.com.moviefinder.base.BaseActivity;
import moviefinder.samusko.com.moviefinder.ui.main.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void onStartClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
