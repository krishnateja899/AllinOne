package com.example.allinone.RecyclerViews.BSecondRecView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.allinone.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UsingGitHubApi extends AppCompatActivity {

    public static final String URL = "https://api.github.com/users";
    RequestQueue queue;
    RecyclerView userList;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_git_hub_api);
        queue = Volley.newRequestQueue(this);
        userList = findViewById(R.id.userList);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        userList.setLayoutManager(layoutManager);
        parseTheData();
    }

    private void parseTheData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "onResponse: " + response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                User[] users = gson.fromJson(response, User[].class);
                userList.setAdapter(new GithubAdapter(UsingGitHubApi.this, users));
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UsingGitHubApi.this, "Something Went Wrong....", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        queue.add(request);
    }

}