package com.example.allinone.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;

public class SplashWelcome extends AppCompatActivity {
    private int progressStatus = 0;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private static int SPLASH_TIME_OUT = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_welcome);
        progressBar = findViewById(R.id.progressBar);
        progress();
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashWelcome.this, OtherFeatures.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void progress() {
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}