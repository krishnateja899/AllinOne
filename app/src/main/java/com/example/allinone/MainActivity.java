package com.example.allinone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.Activities.AutoCompleteSpinners;
import com.example.allinone.Activities.ContinuousGPSUpdate;
import com.example.allinone.Activities.DataBase;
import com.example.allinone.Activities.DialogActivity;
import com.example.allinone.Activities.FingerPrintActivity;
import com.example.allinone.Activities.GPSTurnON;
import com.example.allinone.Activities.LoginActivity;
import com.example.allinone.Activities.PermissionsActivity;
import com.example.allinone.Activities.QRCodes.QRCodeActivity;
import com.example.allinone.Activities.SearchFilter.SearchFilterActivity;
import com.example.allinone.Activities.SharedPreference;
import com.example.allinone.Activities.SignatureView;
import com.example.allinone.Activities.TimeDateActivity;
import com.example.allinone.Activities.VoiceToText;
import com.example.allinone.Activities.CameraGalleryPaths.CameraGalleryPathSaveActivity;
import com.example.allinone.CropImage.CropImageActivity;
import com.example.allinone.Messaging_Socket.MessagingActivity;
import com.example.allinone.UploadImageClasses.UploadImages;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button timeDateBtn = findViewById(R.id.time_btn);
        Button dialogBtn = findViewById(R.id.dialog_btn);
        Button uploadImages = findViewById(R.id.upload_images);
        Button permissions = findViewById(R.id.permissions);
        Button gps_location_update = findViewById(R.id.gps_location_update);
        Button camera_galleryPath_save = findViewById(R.id.camera_galleryPath_save);
        Button signatureView = findViewById(R.id.signatureView);
        Button voice2Text = findViewById(R.id.voice2Text);
        Button messaging_socket = findViewById(R.id.messaging_socket);
        Button gps_turnON = findViewById(R.id.gps_turnON);
        Button imageCrop = findViewById(R.id.imageCrop);
        Button loginBtn = findViewById(R.id.loginBtn);
        Button qr_code = findViewById(R.id.qr_code);
        Button fingerPrint = findViewById(R.id.fingerPrint);
        Button AutoCompleteTextView = findViewById(R.id.autoComplete);
        Button SharedPref = findViewById(R.id.sharedPref);
        Button DataBase = findViewById(R.id.dataBase);
        Button searchFilter = findViewById(R.id.searchFilter);

        signatureView.setOnClickListener(this);
        voice2Text.setOnClickListener(this);
        messaging_socket.setOnClickListener(this);
        gps_turnON.setOnClickListener(this);
        imageCrop.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        qr_code.setOnClickListener(this);
        fingerPrint.setOnClickListener(this);
        AutoCompleteTextView.setOnClickListener(this);
        SharedPref.setOnClickListener(this);
        DataBase.setOnClickListener(this);
        searchFilter.setOnClickListener(this);

        timeDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TimeDateActivity.class);
                startActivity(i);
            }
        });

        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(i1);
            }
        });

        uploadImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, UploadImages.class);
                startActivity(i2);
                /* This is a test to check on how to commit to GITHUB. */
            }
        });

        permissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(MainActivity.this, PermissionsActivity.class);
                startActivity(i3);
            }
        });

        gps_location_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(MainActivity.this, ContinuousGPSUpdate.class);
                startActivity(i4);
            }
        });

        camera_galleryPath_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(MainActivity.this, CameraGalleryPathSaveActivity.class);
                startActivity(i5);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signatureView:
                Intent i6 = new Intent(MainActivity.this, SignatureView.class);
                startActivity(i6);
                break;

            case R.id.voice2Text:
                Intent i7 = new Intent(MainActivity.this, VoiceToText.class);
                startActivity(i7);
                break;

            case R.id.messaging_socket:
                Intent i8 = new Intent(MainActivity.this, MessagingActivity.class);
                startActivity(i8);
                break;

            case R.id.gps_turnON:
                Intent i9 = new Intent(MainActivity.this, GPSTurnON.class);
                startActivity(i9);
                break;

            case R.id.imageCrop:
                Intent i10 = new Intent(MainActivity.this, CropImageActivity.class);
                startActivity(i10);
                break;

            case R.id.loginBtn:
                Intent i11 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i11);
                break;

            case R.id.qr_code:
                Intent i12 = new Intent(MainActivity.this, QRCodeActivity.class);
                startActivity(i12);
                break;

            case R.id.fingerPrint:
                Intent i13 = new Intent(MainActivity.this, FingerPrintActivity.class);
                startActivity(i13);
                break;

            case R.id.autoComplete:
                Intent i14 = new Intent(MainActivity.this, AutoCompleteSpinners.class);
                startActivity(i14);
                break;

            case R.id.sharedPref:
                Intent i15 = new Intent(MainActivity.this, SharedPreference.class);
                startActivity(i15);
                break;

            case R.id.dataBase:
                Intent i16 = new Intent(MainActivity.this, DataBase.class);
                startActivity(i16);
                break;

            case R.id.searchFilter:
                Intent i17 = new Intent(MainActivity.this, SearchFilterActivity.class);
                startActivity(i17);
                break;

        }

    }
}