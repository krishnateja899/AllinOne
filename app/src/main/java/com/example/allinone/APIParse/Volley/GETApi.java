package com.example.allinone.APIParse.Volley;

import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GETApi {
    public static final String TAG = "TAG";

    /**
     * API - GET:
     * {
     * "Message": "",
     * "Success": true,
     * "Data": {
     * "OrderId": 1,
     * "OrderDetailId": 1,
     * "OrderNumber": "XPO/01",
     * "VehicleNumber": "avfr4359",
     * "Origin": "Delhi",
     * "Via1": null,
     * "Via2": null,
     * "Destination": "CHANDIGARH",
     * "CompanyName": "Option Matrix",
     * "Address": "1-1-111/1, Sarojini, Delhi, 111111",
     * "Latitude": "28.573963",
     * "Longtude": "77.191907",
     * "Distance": 243.6,
     * "TransitTime": 4.5,
     * "PlacementDate": "2020-09-11T19:21:13.46",
     * "FromPickupTime": "2020-10-25T10:00:00",
     * "ToPickupTime": "2020-10-25T13:30:00",
     * "DriverId": null,
     * "TripId": 217
     * }
     * }
     */

    private void ParseGET() {
        RequestQueue mQueue = null;//Remove null when Implementing. Then add below Line.
        //mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "ENTER URL", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String success = response.getString("Success");
                    if (success.equals("true")) {
                        JSONObject data = response.getJSONObject("Data");
                        String OrderNumber = data.getString("OrderNumber");
                    } else {
                        Log.d(TAG, "onResponse: ");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onResponse: ");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("UserType", "Driver");
                params.put("AuthToken", "sdfdsg-Fw34ffs-4KDf");
                params.put("UserId", "3D-ft56");
                return params;
            }
        };
        mQueue.add(request);
    }

    /**
     * LOGIN USING GET- BASIC AUTH
     * Request:
     * > To check in the POSTMAN:
     * > Using Basic Auth that requires Username and Password.
     * > Got to Postman and select "Auth" and in Type select 'Basic Auth' and enter Username & Password.
     * <p>
     * > In the android studio using volley:
     * > In the getHeaders section, add Auth as in the Example below.
     * <p>
     * Response:
     * {
     * "id": 11897,
     * "login": "sao.ricardos",
     * "nome": "Ricardo Henrique de Sousa"
     * }
     */

    private void LoginGETUsingBasicAuth() {
        RequestQueue mQueue = null;//Remove null when Implementing. Then add below Line.
        //mQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, "ENTER URL", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String id = object.getString("id");
                    String login = object.getString("login");
                    String name = object.getString("nome");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String auth1 = "Basic "
                        + Base64.encodeToString(("userName.getText().toString()" + ":" + "password.getText().toString()").getBytes(),
                        Base64.NO_WRAP);
                params.put("Authorization", auth1);
                return params;
            }
        };
        mQueue.add(request);
    }

}
