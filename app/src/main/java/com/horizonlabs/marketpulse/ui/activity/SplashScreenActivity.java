package com.horizonlabs.marketpulse.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.horizonlabs.marketpulse.R;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivLogo;
    public static final int SPLASH_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_splash_screen);
        ivLogo = findViewById(R.id.ivLogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SplashScreenActivity.this, (View) ivLogo, "logo");
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class), options.toBundle());
                //startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        }, SPLASH_TIME);
    }

}
