package com.example.allinone.UploadImageClasses.ViewImageClasses;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.allinone.R;

import java.io.File;
import java.io.FileOutputStream;

public class CanvasViewActivity1 extends Activity implements View.OnClickListener {
    CanvasView1 draw_view;
    int selectedColor;
    Boolean writee = true;
    ImageView iwrite, iundo, ireset, icolor;
    String path;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstanceState = getIntent().getExtras();
        path = savedInstanceState.getString("image_path");
        setContentView(R.layout.activity_canvas_view);
        draw_view = (CanvasView1) findViewById(R.id.draw_view);

        Bitmap bmImg;
        bmImg = BitmapFactory.decodeFile(path);


        try {
            ExifInterface exif = new ExifInterface(path);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
            } else if (orientation == 3) {
                matrix.postRotate(180);
            } else if (orientation == 8) {
                matrix.postRotate(270);
            }
            bmImg = Bitmap.createBitmap(bmImg, 0, 0, bmImg.getWidth(), bmImg.getHeight(), matrix, true); // rotating bitmap
        } catch (Exception e) {
            e.printStackTrace();
        }
        draw_view.setImageBitmap(bmImg);


        selectedColor = 0;
        draw_view.setBrushColor(Color.RED);
        draw_view.Write = writee;

        iwrite = (ImageView) findViewById(R.id.iwrite);
        iwrite.setOnClickListener(this);
        iwrite.setColorFilter(getResources().getColor(R.color.colorPrimary));


        iundo = (ImageView) findViewById(R.id.iundo);
        iundo.setOnClickListener(this);

        ireset = (ImageView) findViewById(R.id.ireset);
        ireset.setOnClickListener(this);

      /*  isave = (ImageView) findViewById(R.id.isave);
        isave.setOnClickListener(this);*/

        icolor = (ImageView) findViewById(R.id.icolor);
        icolor.setOnClickListener(this);

        //  istopwrite = (ImageView) findViewById(R.id.istopwrite);
        // istopwrite.setOnClickListener(this);


        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);


        //   discard = (Button) findViewById(R.id.discard);
        //   discard.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iundo:
                draw_view.onClickUndo();
                break;
          /*  case R.id.isave:
                saveImg();
                break;*/
            case R.id.icolor:
                String colorsArray[] = {"RED", "BLACK", "BLUE", "CYAN", "GREEN"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Pen Color");
                builder.setSingleChoiceItems(colorsArray, selectedColor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                selectedColor = 0;
                                draw_view.setBrushColor(Color.RED);
                                break;
                            case 1:
                                selectedColor = 1;
                                draw_view.setBrushColor(Color.BLACK);
                                break;
                            case 2:
                                selectedColor = 2;
                                draw_view.setBrushColor(Color.BLUE);
                                break;
                            case 3:
                                selectedColor = 3;
                                draw_view.setBrushColor(Color.CYAN);
                                break;
                            case 4:
                                selectedColor = 4;
                                draw_view.setBrushColor(Color.GREEN);
                                break;
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
            case R.id.ireset:
                draw_view.resetPaths();
                save.setEnabled(true);

                break;
            case R.id.iwrite:
                writee = true;
                iwrite.setColorFilter(getResources().getColor(R.color.colorPrimary));
                // istopwrite.setColorFilter(Color.BLACK);
                draw_view.Write = writee;
                break;
           /* case R.id.istopwrite:
                iwrite.setColorFilter(Color.BLACK);
                istopwrite.setColorFilter(getResources().getColor(R.color.colorPrimary));
                writee = false;
                draw_view.Write = writee;
                break;*/

            case R.id.save:
                save.setEnabled(false);

                saveImg();
                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.putExtra("image_path", path);
                setResult(RESULT_OK, intent);
                finish();
                break;
           /* case R.id.discard:
                setResult(RESULT_CANCELED);
                finish();
                break;*/

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        finish();
    }

    private void saveImg() {
        Bitmap image = Bitmap.createBitmap(draw_view.getWidth(), draw_view.getHeight(), Bitmap.Config.RGB_565);
        draw_view.draw(new Canvas(image));
       /* String uri = MediaStore.Images.Media.insertImage(getContentResolver(), image, "title", null);
        Log.e("uri", uri);
        try {
            // Save the image to the SD card.
            File folder = new File(Environment.getExternalStorageDirectory() + "/DrawTextOnImg");
            if (!folder.exists()) {
                folder.mkdir();
                //folder.mkdirs();  //For creating multiple directories
            }
            File file = new File(Environment.getExternalStorageDirectory() + "/DrawTextOnImg/tempImg.png");
            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Toast.makeText(CanvasViewActivity.this, "Picture saved", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(CanvasViewActivity.this, "Save failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }*/


        // for storing inside package

        try {
            File file = new File(path);
            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 50, stream);
            Toast.makeText(CanvasViewActivity1.this, "Image Attached", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(CanvasViewActivity1.this, "Unable to attach. Please try again", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
