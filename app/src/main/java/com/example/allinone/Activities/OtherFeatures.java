package com.example.allinone.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;

public class OtherFeatures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_features);

        Button handler = findViewById(R.id.handler);
        Button splashScreen = findViewById(R.id.splashScreen);

        handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(OtherFeatures.this);
                progressDialog.setTitle("Attention");
                progressDialog.setMessage("You'll get a toast, wait..");
                progressDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
                progressDialog.show();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(OtherFeatures.this, "Handler Test", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, 3000);
            }
        });

        splashScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OtherFeatures.this, SplashWelcome.class);
                startActivity(i);
            }
        });
    }
}