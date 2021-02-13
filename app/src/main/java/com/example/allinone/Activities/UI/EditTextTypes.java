package com.example.allinone.Activities.UI;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;

public class EditTextTypes extends AppCompatActivity {

    private AutoCompleteTextView autocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_types);
        autocomplete = findViewById(R.id.autocomplete);

        String items[] = {"Material", "Design", "Components", "Android"};
        ArrayAdapter<String> attemptAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        autocomplete.setAdapter(attemptAdapter);

    }
}