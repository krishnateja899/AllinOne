package com.example.allinone.UploadImageClasses.ViewImageClasses;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DisplayImage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_display_image);
        String url = "";
        if (getIntent() != null)
            url = getIntent().getStringExtra("image_url");
        final ProgressBar bar = findViewById(R.id.bar);
        final ImageView iv = findViewById(R.id.display_img);
        Log.e("image",url);
//        Glide.with(this)
//                .load(url)
//                .into(iv);

        Picasso.get().load(url).into(iv, new Callback() {
            @Override
            public void onSuccess() {
                bar.setVisibility(View.GONE);
                iv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Exception e) {
                bar.setVisibility(View.GONE);
                Log.e("error",e.toString());
                Toast.makeText(getApplicationContext(),"Unable To Load Image",Toast.LENGTH_SHORT).show();
                finish();
            }

        });
    }
}

