package com.example.allinone.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.allinone.R;


public class DialogFromClass extends Dialog {

    private Context context;
    private Activity activity;


    public DialogFromClass(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dialog_from_class);

        Button cancel = findViewById(R.id.cancelar);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}