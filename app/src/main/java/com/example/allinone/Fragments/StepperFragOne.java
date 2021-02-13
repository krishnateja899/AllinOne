package com.example.allinone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.allinone.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;


public class StepperFragOne extends Fragment implements BlockingStep { //or use just Step

    private EditText editTextStep;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stepper_frag_one, container, false);
        editTextStep = v.findViewById(R.id.editTextStep);


        return v;
    }

    private void Validate(StepperLayout.OnNextClickedCallback callback) {
        if (editTextStep.getText().length() == 0) {
            editTextStep.setError("Required");
        } else {
            callback.goToNextStep();
        }

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        Validate(callback);
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }
}