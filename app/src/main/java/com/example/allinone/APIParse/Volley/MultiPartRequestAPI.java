package com.example.allinone.APIParse.Volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MultiPartRequestAPI {
    public static final String TAG = "TAG";

    //

    /**
     * > Use this dependency instead of volley: implementation 'dev.dworks.libs:volleyplus:+'
     * > Cannot use both volley and this dependency together in a project.
     * > Official project repo: https://github.com/DWorkS/VolleyPlus
     */

    private void SimpleMultipart() {
//        SimpleMultiPartRequest request = new SimpleMultiPartRequest(Request.Method.POST, "http://10.0.2.2:4000/upload", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e(TAG, "onResponse: "+response );
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "onErrorResponse: "+error.getMessage());
//            }
//        }){
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                return super.getHeaders();
//            }
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return super.getParams();
//            }
//        };
//        request.addFile("profile", currentPhotoPath);
//        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
//        mRequestQueue.add(request);
    }

    /**
     * > The File `VolleyMultipartRequest` is used for this. Add that class to your project.
     * > You need to send the image name, which you get from `File` or string.
     * > Then need to convert the image in to bytes and pass it as shown below.
     * > How to convert image in to bytes? : `bytes = Files.readAllBytes(Paths.get(currentPhotoPath))`;
     * > Where ever you get the image path there convert the path in to bytes and pass that variable to the class.
     */

    private void MultiPartRequest() {
        File image = null;
        byte[] bytes = new byte[0];
        VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST, "http://10.0.2.2:4000/upload", new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                Log.e(TAG, "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                params.put("profile", new DataPart(image.getName() + ".png", bytes));
                return params;
            }
        };
    }

}
