package com.example.allinone.Activities.SearchFilter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFilterActivity extends AppCompatActivity {

    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        fillExampleList();
        setUpRecyclerView();
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.user_img, "One", "Ten"));
        exampleList.add(new ExampleItem(R.drawable.icon_send, "Two", "Eleven"));
        exampleList.add(new ExampleItem(R.drawable.ic_camera, "Three", "Twelve"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_music_note_24, "Four", "Thirteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_code_scanner_auto_focus_off, "Five", "Fourteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_code_scanner_auto_focus_on, "Six", "Fifteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_mic, "Seven", "Sixteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_paint_brush, "Eight", "Seventeen"));
        exampleList.add(new ExampleItem(R.drawable.ic_paint_palette_and_brush, "Nine", "Eighteen"));
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}