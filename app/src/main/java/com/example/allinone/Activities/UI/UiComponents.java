package com.example.allinone.Activities.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;

public class UiComponents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_components);

        Button backgroundColors = findViewById(R.id.backgroundColors);
        Button typesOfButtons = findViewById(R.id.typesOfButtons);
        Button editTextTypes = findViewById(R.id.editTextTypes);

        backgroundColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(UiComponents.this, BackgroundsColors.class);
                startActivity(i1);
            }
        });

        typesOfButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(UiComponents.this, ButtonTypes.class);
                startActivity(i2);
            }
        });

        editTextTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(UiComponents.this, EditTextTypes.class);
                startActivity(i3);
            }
        });
    }
}