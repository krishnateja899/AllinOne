package com.example.allinone.UploadImageClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.allinone.R;

import java.util.ArrayList;

public class ViewImagesActivity extends AppCompatActivity {
    private RecyclerViewWithEmptyViewSupport rv;
    ArrayList<SpareImages> spareImages;
    ViewImagesAdapter adapter;
    int from =1;
    RepairsImageDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_images);

        TextView emptyView = findViewById(R.id.no_files);
        rv = findViewById(R.id.files_rv);
        rv.setEmptyView(emptyView);
        spareImages= getIntent().getParcelableArrayListExtra("images_list");
        from= getIntent().getIntExtra("from", 0);
        Toolbar toolbar = findViewById(R.id.toolbar_files_url_sparess);


        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(null);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true);
        manager.setStackFromEnd(true);
        rv.setLayoutManager(manager);
        dao= new RepairsImageDao(0, null, null,  spareImages);
        adapter = new ViewImagesAdapter(dao,this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (from==1){
            Intent intent = new Intent("images_file_count");
            intent.putExtra("images", adapter.returnImages());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            finish();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        startActivity(new Intent(this,ReportCase_Dialog.class));

        if (from==1){
            Intent intent = new Intent("images_file_count");
            intent.putExtra("images", adapter.returnImages());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            finish();
        }


        return false;
    }
}
