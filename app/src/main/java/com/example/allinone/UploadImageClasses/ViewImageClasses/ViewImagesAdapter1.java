package com.example.allinone.UploadImageClasses.ViewImageClasses;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

public class ViewImagesAdapter1 extends RecyclerView.Adapter<ViewImagesAdapter1.Holder> {
    RepairsImageDao1 dao;
    ViewImagesActivity1 activity;
    ArrayList<SpareImages1> list;
    int attId;
    private ProgressDialog dialog;

    public ViewImagesAdapter1(RepairsImageDao1 dao, ViewImagesActivity1 activity) {
        this.dao = dao;
        this.activity = activity;
        this.list = dao.getImages();


        dialog = new ProgressDialog(this.activity);
        dialog.setMessage("Deleting Image");
        dialog.setCancelable(false);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_showfiles, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int i) {
        final SpareImages1 images = list.get(i);
        String im = images.getName();
        Log.e("images",images.getName());
        Log.e( "onBindViewHolder: ", ""+ images.getId());

        if (images.getId()==0){

            final String path = images.getPath();

            Log.e("imggg",path);
            String name = images.getName();

            final String[] array = name.split("_",2);
            final String array1 = array[1];
            holder.fileName.setText(array1);

            if (path.contains("jpg") || path.contains("jpeg") || path.contains("png") || path.contains("JPG")) {
                Picasso.get().load(path).into(holder.fileFormat, new Callback() {

                    @Override
                    public void onSuccess() {
                        holder.layout.setVisibility(View.GONE);
                        holder.fileFormat.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        holder.layout.setVisibility(View.GONE);
                        holder.fileFormat.setVisibility(View.GONE);
                    }


                });
            }
            else if (array1.contains("pdf")) {
                holder.layout.setVisibility(View.GONE);
                holder.fileFormat.setVisibility(View.VISIBLE);

            } else if (array1.contains(".doc") || array1.contains(".docx")) {
                holder.layout.setVisibility(View.GONE);
                holder.fileFormat.setVisibility(View.VISIBLE);

            } else if (array1.contains(".xls") || array1.contains(".xlsx")) {
                holder.layout.setVisibility(View.GONE);
                holder.fileFormat.setVisibility(View.VISIBLE);

            } else if (array1.contains(".zip") || array1.contains(".rar")) {
                holder.layout.setVisibility(View.GONE);
                holder.fileFormat.setVisibility(View.VISIBLE);

            } else if (array1.contains(".txt")) {
                holder.layout.setVisibility(View.GONE);
                holder.fileFormat.setVisibility(View.VISIBLE);

            }

//            if (attachments != null)
//               // holder.fileName.setText(array[5]);
//            else
//                holder.fileFormat.setVisibility(View.GONE);

            holder.restLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (path.contains("jpg") || path.contains("jpeg") || path.contains("png") || path.contains("JPG")) {
                        Intent intent = new Intent(activity, DisplayImage1.class);
                        intent.putExtra("image_url", path);
                        activity.startActivity(intent);
                    }
                    if(path.contains(".3gp")){
                        MediaPlayer mPlayer = new MediaPlayer();
                        try {
                            mPlayer.setDataSource(path);
                            mPlayer.prepare();
                            mPlayer.start();
                            Toast.makeText(activity, "Recording Started Playing", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {

                        }

                    }
                    else
                        Toast.makeText(activity, "You cannot view this type of image", Toast.LENGTH_SHORT).show();
                }
            });
            holder.deleteImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    attId = list.get(holder.getAdapterPosition()).getSpareId();
                    dialog.show();

                }
            });

        }
        if (images.getId()==3){
            holder.fileName.setText(images.getName());
            if(images.getName().contains(".3gp")){
                holder.pro.setVisibility(View.GONE);
                holder.fileFormat.setVisibility(View.VISIBLE);
                holder.fileFormat.setImageResource(R.drawable.ic_baseline_music_note_24);
            }
            holder.restLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String a = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/Spares1/" + images.getName();
                    MediaPlayer mPlayer = new MediaPlayer();
                    try {
                        mPlayer.setDataSource(a);
                        mPlayer.prepare();
                        mPlayer.start();
                        Toast.makeText(activity, "Recording Started Playing", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {

                    }

                }
            });
            holder.deleteImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    File dir;

                    dir = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/Spares1");
                    if(new File(dir, list.get(holder.getAdapterPosition()).getName()).delete())
                        Log.e("check_file","Deleted");
                    list.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();

                }
            });
        }

        if (images.getId()==1){
            if (list != null) {
                //Toast.makeText(activity, "hai", Toast.LENGTH_SHORT).show();
                holder.fileName.setText(images.getName());
               /* if(images.getName().contains(".3gp")){
                   holder.pro.setVisibility(View.GONE);
                   holder.fileFormat.setVisibility(View.VISIBLE);
                   holder.fileFormat.setImageResource(R.drawable.ic_baseline_music_note_24);
                }*/

                File imgFile = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/Spares1/" + images.getName());
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    holder.fileFormat.setImageBitmap(myBitmap);
                    holder.layout.setVisibility(View.GONE);
                    holder.fileFormat.setVisibility(View.VISIBLE);
                }

                holder.restLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        File file;
                        file = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/Spares1/" + images.getName());

                        openFile(activity, file);
                    }
                });
                holder.deleteImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        File dir;

                        dir = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/Spares1");
                        if(new File(dir, list.get(holder.getAdapterPosition()).getName()).delete())
                            Log.e("check_file", "Deleted");
                        list.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
            }
        }


    }

    public ArrayList<SpareImages1> returnImages() {
        return list;
    }




    @Override
    public int getItemCount() {
        return list.size();
    }



    private void openFile(Context context, File url) {
        Uri uri;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
            uri = Uri.fromFile(url);
        else
            uri = FileProvider.getUriForFile(context,context.getPackageName(),url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                // Word document
                intent.setDataAndType(uri, "application/msword");
            } else if (url.toString().contains(".pdf")) {
                // PDF file
                intent.setDataAndType(uri, "application/pdf");
            } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                // Excel file
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
                // WAV audio file
                intent.setDataAndType(uri, "application/x-wav");
            } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                // JPG file
                intent.setDataAndType(uri, "image/jpeg");
            } else if (url.toString().contains(".txt")) {
                // Text file
                intent.setDataAndType(uri, "text/plain");
            } else {
                //if you want you can also define the intent type for any other file

                //additionally use else clause below, to manage other unknown extensions
                //in this case, Android will show all applications installed on the device
                //so you can choose which application to use
                intent.setDataAndType(uri, "*/*");
            }
            intent.setFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e){
            Toast.makeText(context,"No App Found To Open This File !",Toast.LENGTH_SHORT).show();
        }

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView fileName;
        FrameLayout layout;
        LinearLayout restLayout,mainView;
        ImageView fileFormat, deleteImg;
        ProgressBar pro;
        public Holder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.files_bar);
            mainView = itemView.findViewById(R.id.main_view);
            fileName = itemView.findViewById(R.id.file_name);
            deleteImg = itemView.findViewById(R.id.delete_img);
            restLayout = itemView.findViewById(R.id.rest_layout);
            fileFormat = itemView.findViewById(R.id.file_format);
            pro = itemView.findViewById(R.id.pro);
        }
    }
}