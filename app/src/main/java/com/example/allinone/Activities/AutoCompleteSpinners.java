package com.example.allinone.Activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.Adapters.AutoSuggestAdapter;
import com.example.allinone.R;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteSpinners extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);

        AutoCompleteTextView textView = findViewById(R.id.textView);
        AutoCompleteTextView textView2 = findViewById(R.id.textView2);
        AutoCompleteTextView textView3 = findViewById(R.id.textView3);
        Spinner spinner = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        String[] arrayFromStrings = getResources().getStringArray(R.array.auto_complete_example);
        String[] items = new String[]{"1", "2 ", "3"};
        ArrayAdapter<String> attemptAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayFromStrings);

        List<String> stringList = new ArrayList<String>();
        stringList.add("Black");
        stringList.add("White");
        stringList.add("Yellow");
        stringList.add("Green");
        stringList.add("Blue");
        stringList.add("Brown");
        stringList.add("Orange");
        stringList.add("Pink");
        stringList.add("Violet");
        stringList.add("Cyan");
        stringList.add("LightBlue");

        //First TextView
        AutoSuggestAdapter adapter = new AutoSuggestAdapter(this, android.R.layout.simple_list_item_1, stringList);
        textView.setAdapter(adapter);
        textView.setThreshold(1);


        //Second TextView
        ArrayAdapter<String> adapter2;
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stringList);
        textView2.setAdapter(adapter2);
        textView2.setThreshold(1);

        //Third TextView
        textView3.setAdapter(attemptAdapter);
        textView3.setThreshold(1);

        //First Spinner
        ArrayAdapter<String> adapter3;
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stringList);
        spinner.setAdapter(adapter3);

        //Second Spinner
        ArrayAdapter<String> adapter4;
        adapter4 = new ArrayAdapter<String>(this, R.layout.spinner_list, stringList);
        spinner2.setAdapter(adapter4);
    }
}