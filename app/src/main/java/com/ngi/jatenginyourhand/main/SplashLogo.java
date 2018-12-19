package com.ngi.jatenginyourhand.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;

import com.ngi.jatenginyourhand.R;

/**
 * Created by DHIMAS on 3/18/2018.
 */

public class SplashLogo extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashThemeNGI);
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                intent = new Intent (SplashLogo.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}
