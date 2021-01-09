package com.example.allinone.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.HelperClasses.AppPreferences;
import com.example.allinone.HelperClasses.Utils;
import com.example.allinone.R;

import java.io.ByteArrayOutputStream;

import static com.example.allinone.HelperClasses.Utils.REQUEST_IMAGE_CAPTURE;

public class SharedPreference extends AppCompatActivity {
    private AppPreferences preferences;
    private ImageView prefPath;
    private Button dialogTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        ImageView openCamera = findViewById(R.id.openCamera);
        prefPath = findViewById(R.id.prefPath);
        preferences = new AppPreferences(this);
        dialogTest = findViewById(R.id.dialogTest);

        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.dispatchTakePictureIntent(SharedPreference.this, REQUEST_IMAGE_CAPTURE);
            }
        });

        dialogTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.DialogClass(SharedPreference.this, "Test", "This is a Message", "OK");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap photo, OutImage;
            photo = (Bitmap) data.getExtras().get("data");
            OutImage = Bitmap.createScaledBitmap(photo, 300, 400, true);
            preferences.setProfileImage(encodeTobase64(OutImage));
            prefPath.setImageBitmap(decodeBase64(preferences.getProfileImage()));
        }
    }

    // method for bitmap to base64
    public static String encodeTobase64(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    // method for base64 to bitmap
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}