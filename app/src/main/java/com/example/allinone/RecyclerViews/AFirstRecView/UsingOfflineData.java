package com.example.allinone.RecyclerViews.AFirstRecView;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;

import java.util.Arrays;

public class UsingOfflineData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_data);

        RecyclerView recyclerView = findViewById(R.id.recycler_view1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        String[] languages1 = {"Java", "Javascript", "C#", "php", "C", "C++", "Python", "Java", "Javascript", "C#", "php", "C", "C++", "Python"};
        recyclerView.setAdapter(new ProgrammingAdapter(Arrays.asList(languages1)));


//        Type 2: uncomment this and comment the above.
//        List<String> languages=new ArrayList<>();
//        for (int j=1; j<=100; j++)
//            languages.add(String.valueOf(j));
//        recyclerView.setAdapter(new ProgrammingAdapter(languages));

    }
}