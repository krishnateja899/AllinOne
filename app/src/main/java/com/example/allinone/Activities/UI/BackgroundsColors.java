package com.example.allinone.Activities.UI;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;

public class BackgroundsColors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgrounds_colors);
        LinearLayout your_Layout = (LinearLayout) findViewById(R.id.main_container);
        AnimationDrawable animationDrawable = (AnimationDrawable) your_Layout.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }
}