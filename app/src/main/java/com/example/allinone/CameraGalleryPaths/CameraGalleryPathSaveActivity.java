package com.example.allinone.CameraGalleryPaths;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.allinone.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraGalleryPathSaveActivity extends AppCompatActivity {

    private ImageView camera, imageView, gallery;
    private TextView filepaths;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_CODE_GALLERY = 2;

    String currentPhotoPath;
    File photoFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery_path_save);

        camera=findViewById(R.id.camera);
        imageView=findViewById(R.id.imageView);
        gallery=findViewById(R.id.gallery);
        filepaths=findViewById(R.id.filepaths);

        ActivityCompat.requestPermissions(CameraGalleryPathSaveActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            photoFile = createImageFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (photoFile != null) {
            Uri photoURI = FileProvider.getUriForFile(this, getPackageName(), photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();

        Log.d("TAG", "createImageFile: "+currentPhotoPath);
        return image;
    }

    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            Log.d("TAG", "onActivityResult: "+extras);
//            imageView.setImageBitmap(imageBitmap);
            filepaths.setText(currentPhotoPath);
            Picasso.get().load(new File(currentPhotoPath)).into(imageView);
        }
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null) {
            int dataSize = 0;
            Uri uri = data.getData();
            try {
                InputStream fileInputStream = null;
                if (uri != null)
                    fileInputStream = getContentResolver().openInputStream(uri);
                if (fileInputStream != null)
                    dataSize = fileInputStream.available();
                if (dataSize <= 10485760) {
                    photoFile = createImageFile();
//                    Uri photoURI = FileProvider.getUriForFile(this, getPackageName(), photoFile);
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
                    Log.d("TAG", "FileOutputStream: "+"this"+fileOutputStream);
                    if (inputStream != null)
                        copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    assert inputStream != null;
                    inputStream.close();
                    filepaths.setText(currentPhotoPath);
                    Picasso.get().load(new File(currentPhotoPath)).into(imageView);

                } else {
                    String msg = "To Increase the Performance Of App, Files with Size more than 10MB are not Allowed.";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    //alternate working using ImageFilePath.Java file for Gallery

// if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null) {
//         Uri uri = data.getData();
//         String realPath = ImageFilePath.getPath(MainActivity.this, data.getData());
////                realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());
//
//         Log.i("TAG", "onActivityResult: file path : " + realPath);
//         try {
//         Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//         // Log.d(TAG, String.valueOf(bitmap));
//
//         Picasso.get().load(new File(realPath)).into(imageView);
//
//
////                imageView.setImageBitmap(bitmap);
////                imageView.setVisibility(View.VISIBLE);
//
//         } catch (IOException e) {
//         e.printStackTrace();
//         }
//         } else {
//         Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
//         }
}