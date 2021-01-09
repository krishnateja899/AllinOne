package com.example.allinone.HelperClasses;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class Utils {
    public static final int REQUEST_IMAGE_CAPTURE = 1;


    public static void dispatchTakePictureIntent(Activity activity, int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(takePictureIntent, requestCode);
    }

    public static String ConvertArrayListToString(ArrayList<String> arrList) {
        StringBuilder sb = new StringBuilder();
        for (String s : arrList) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static AlertDialog DialogClass(Context context, String title, String message, String positiveBtn) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(false);
        builder1.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //Creating dialog box
        AlertDialog alert1 = builder1.create();
        alert1.show();
        return alert1;
    }
}
