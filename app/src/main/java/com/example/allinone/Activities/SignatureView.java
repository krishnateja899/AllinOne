package com.example.allinone.Activities;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class SignatureView extends AppCompatActivity {
    private Bitmap bitmap;
    private Button clear,save;
    private com.kyanogen.signatureview.SignatureView signatureView;
    private String path;
    private File f;
    private ImageView image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature_view);

        signatureView =  (com.kyanogen.signatureview.SignatureView) findViewById(R.id.signature_view);
        clear = (Button) findViewById(R.id.clear);
        save = (Button) findViewById(R.id.save);
        image_view=findViewById(R.id.image_view);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clearCanvas();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = signatureView.getSignatureBitmap();
                path = saveImage(bitmap);

                signatureView.clearCanvas();
                Log.d("TAG", "onClick: "+f.getAbsolutePath());

                Picasso.get().load(new File(path)).into(image_view);
            }
        });
    }

    private String saveImage(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/SignatureImage/");
//        Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY /*iDyme folder*/);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
            Log.d("hhhhh",wallpaperDirectory.toString());
        }
        try {
            f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(SignatureView.this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
            Log.d("TAG", "File Name::--->" + f.getName());


            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
}