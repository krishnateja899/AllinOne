package com.example.allinone.RecyclerViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;
import com.example.allinone.RecyclerViews.AFirstRecView.UsingOfflineData;
import com.example.allinone.RecyclerViews.BSecondRecView.UsingGitHubApi;
import com.example.allinone.RecyclerViews.CThirdRecView.ThirdRecView;
import com.example.allinone.RecyclerViews.DFourthRecView.AnimeRecView;
import com.example.allinone.RecyclerViews.EFifthRecView.FifthRecView;
import com.example.allinone.RecyclerViews.FSixthRecView.UsersInfoApi;
import com.example.allinone.RecyclerViews.GSeventhRecView.DotIndicatorRecView;
import com.example.allinone.RecyclerViews.HEighthRecView.TripsActivity;

public class RecyclerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);

        Button recView1 = findViewById(R.id.recView1);
        Button recView2 = findViewById(R.id.recView2);
        Button recView3 = findViewById(R.id.recView3);
        Button recView4 = findViewById(R.id.recView4);
        Button recView5 = findViewById(R.id.recView5);
        Button recView6 = findViewById(R.id.recView6);
        Button recView7 = findViewById(R.id.recView7);
        Button recView8 = findViewById(R.id.recView8);
        Button recView9 = findViewById(R.id.recView9);

        recView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(RecyclerMainActivity.this, UsingOfflineData.class);
                startActivity(i1);
            }
        });

        recView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(RecyclerMainActivity.this, UsingGitHubApi.class);
                startActivity(i2);
            }
        });

        recView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(RecyclerMainActivity.this, ThirdRecView.class);
                startActivity(i3);
            }
        });

        recView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(RecyclerMainActivity.this, AnimeRecView.class);
                startActivity(i4);
            }
        });

        recView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(RecyclerMainActivity.this, FifthRecView.class);
                startActivity(i5);
            }
        });

        recView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6 = new Intent(RecyclerMainActivity.this, UsersInfoApi.class);
                startActivity(i6);
            }
        });

        recView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i7 = new Intent(RecyclerMainActivity.this, DotIndicatorRecView.class);
                startActivity(i7);
            }
        });

        recView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i8 = new Intent(RecyclerMainActivity.this, TripsActivity.class);
                startActivity(i8);
            }
        });

        recView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}