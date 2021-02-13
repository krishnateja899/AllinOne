package com.example.allinone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.allinone.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;


public class StepperFragThree extends Fragment implements BlockingStep {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stepper_frag_three, container, false);
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        //Toast.makeText(getContext(), "This is the Last Frag.", Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        Toast.makeText(getContext(), "Finish", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}