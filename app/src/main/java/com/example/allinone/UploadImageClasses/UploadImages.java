package com.example.allinone.UploadImageClasses;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.allinone.R;
import com.example.allinone.UploadImageClasses.ViewImageClasses.CanvasViewActivity1;
import com.example.allinone.UploadImageClasses.ViewImageClasses.SpareImages1;
import com.example.allinone.UploadImageClasses.ViewImageClasses.ViewImagesActivity1;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UploadImages extends AppCompatActivity {

    private static final String TAG = "TAG";
    ImageView camera, gallery, mic;
    TextView imgCount;
    com.mikhaellopez.circularimageview.CircularImageView showImage;


    private String[] permissions;
    private File mFileTemp;
    private String path,fileName;
    private FileCounter counter;
    private ArrayList<SpareImages1> tempImages;
    private int allCount, fileCount = 0, webCount = 0;
    private int id;
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_CODE_GALLERY = 0x2;
    public static final int REQUEST_CODE_WRITE_ON_IMAGE = 0x4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images);

        camera=findViewById(R.id.ic_camera);
        gallery=findViewById(R.id.ic_file);
        mic=findViewById(R.id.ic_mic);
        imgCount=findViewById(R.id.files_attach);
        showImage=findViewById(R.id.showImage);

        path = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/Spares1/";
        permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        tempImages = new ArrayList<>();
        counter = new FileCounter();
        LocalBroadcastManager.getInstance(this).registerReceiver(counter, new IntentFilter("images_file_count"));



        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptCamera();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptGallery();
            }
        });

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadImages.this, "Mic", Toast.LENGTH_SHORT).show();
            }
        });

        imgCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgCount.getText().toString().equals("0 Images Attached"))
                    Toast.makeText(getApplicationContext(), "No Images Attached", Toast.LENGTH_SHORT).show();
                else
                    sendImages();
            }
        });
    }


    public void promptCamera() {
        int permsRequestCode = 200;
        if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                requestPermissions(permissions, permsRequestCode);
            else
                openCamera();
        else
            openCamera();
    }

    private void promptGallery() {
        int permsRequestCode = 200;
        if (ContextCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                requestPermissions(permissions, permsRequestCode);
            else
                openImages();
        else
            openImages();
    }


    public void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            try {
                mFileTemp = createCameraImageFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (mFileTemp != null) {
                Uri photoURI = FileProvider.getUriForFile(this, getPackageName(), mFileTemp);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
    }

    private File createCameraImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "";

        imageFileName = "SPR" + "__" + timeStamp;

        File newFile = new File(path);
        if (!newFile.exists())
            newFile.mkdirs();
        fileName = imageFileName + ".jpg";
        Log.d(TAG, "createCameraImageFile: " + fileName);
        File f = new File(newFile, imageFileName + ".jpg");
//        return new File(newFile, imageFileName + ".jpg");
        Log.d(TAG, "createCameraImageFile1: " + f);
        return f;
    }

    private void openImages() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
    }

    private void sendImages() {
        Intent intent = new Intent(this, ViewImagesActivity1.class);
        intent.putExtra("images_list", tempImages);
        intent.putExtra("from", 1);
        intent.putExtra("web_count", webCount);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "RequestImagecapture: ");
                    Uri photoURI = FileProvider.getUriForFile(this, getPackageName(), mFileTemp);
                    Intent intent = new Intent(this, CanvasViewActivity1.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("image_path", mFileTemp.getPath());
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_CODE_WRITE_ON_IMAGE);

                } else {
                    Log.d(TAG, "RequestImagecaptureElseCase: ");
                    File file = new File(mFileTemp.getPath());
                    file.delete();
                }
                break;
            case REQUEST_CODE_WRITE_ON_IMAGE:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String path2 = data.getStringExtra("image_path");
                        Picasso.get().load(new File(path2)).into(showImage);

                        Log.e("kkk", path2);
                        if (path2 == null)
                            return;
                    }
                    fileCount = fileCount + 1;
                    allCount = fileCount + webCount;
                    String count;
                    if (allCount > 1)
                        count = allCount + " Files Attached";
                    else
                        count = allCount + " Files Attached";
                    imgCount.setText(count);
                    tempImages.add(new SpareImages1(1, id, fileName, path));
                } else {
                    File file = new File(mFileTemp.getPath());
                    file.delete();
                }
                break;
            case REQUEST_CODE_GALLERY:
                if (data != null) {
                    int dataSize = 0;
                    Uri uri = data.getData();
                    try {
                        InputStream fileInputStream = null;
                        if (uri != null)
                            fileInputStream = getContentResolver().openInputStream(uri);
                        if (fileInputStream != null)
                            dataSize = fileInputStream.available();
                        if (dataSize <= 10485760) {
                            mFileTemp = createCameraImageFile();
                            Uri photoURI = FileProvider.getUriForFile(this, getPackageName(), mFileTemp);
                            InputStream inputStream = getContentResolver().openInputStream(data.getData());
                            FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
                            Log.d(TAG, "FileOutputStream: "+"this"+fileOutputStream);
                            if (inputStream != null)
                                copyStream(inputStream, fileOutputStream);
                            fileOutputStream.close();
                            assert inputStream != null;
                            inputStream.close();

                            Intent intent = new Intent(this, CanvasViewActivity1.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("image_path", mFileTemp.getPath());
                            intent.putExtras(bundle);
//                            intent.putExtra(MediaStore.EXTRA_OUTPUT, bundle);
                            startActivityForResult(intent, REQUEST_CODE_WRITE_ON_IMAGE);
                        } else {
                            String msg = "To Increase the Performance Of App, Files with Size more than 10MB are not Allowed.";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    private class FileCounter extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            tempImages = intent.getParcelableArrayListExtra("images");
            imgCount.setText(tempImages.size() + " Files Attached");
            Log.e("1oOptionsItemSelected: ", "" + tempImages.size());
        }
    }

}