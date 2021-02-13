package com.example.allinone.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.Adapters.MyStepperAdapter;
import com.example.allinone.R;

/**
 * Official repo & doc : https://github.com/stepstone-tech/android-material-stepper
 */

public class StepperLayout extends AppCompatActivity {
    private com.stepstone.stepper.StepperLayout mStepperLayout;
    private MyStepperAdapter mStepperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper_layout);

        mStepperLayout = (com.stepstone.stepper.StepperLayout) findViewById(R.id.stepperLayout);
        mStepperAdapter = new MyStepperAdapter(getSupportFragmentManager(), this); //If the stepperLayout is used inside a fragment use getChildFragmentManager() instead.
        mStepperLayout.setAdapter(mStepperAdapter);
    }
}