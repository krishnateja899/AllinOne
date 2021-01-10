package com.example.allinone.RecyclerViews.GSeventhRecView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;

import java.util.Arrays;

public class DotIndicatorRecView extends AppCompatActivity {

    private RecyclerView recyclerView3;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_indicator_rec_view);

        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setNestedScrollingEnabled(false);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager);
        recyclerView3.setHasFixedSize(true);

        String[] languages = {"Rs.500 Reward for On-time Arrival", "Javascript", "C#", "php", "C", "C++", "Python", "Java", "Javascript", "C#", "php", "C", "C++", "Python"};
        recyclerView3.setAdapter(new OfferAdapter(Arrays.asList(languages)));

        final int radius = getResources().getDimensionPixelSize(R.dimen.radius);
        final int dotsHeight = getResources().getDimensionPixelSize(R.dimen.dots_height);
        final int color = ContextCompat.getColor(this, R.color.black);
        recyclerView3.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, color, color));
        new PagerSnapHelper().attachToRecyclerView(recyclerView3);

    }
}