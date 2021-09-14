package com.example.progresstracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //        Initialize handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Redirect to main activity
                startActivity(new Intent(SplashActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                Finish activity
                finish();
            }
        }, 5000);
    }
}
