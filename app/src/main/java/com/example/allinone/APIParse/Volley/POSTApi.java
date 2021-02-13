package com.example.allinone.APIParse.Volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class POSTApi {
    public static final String TAG = "TAG";

    /**
     * API - POST:
     * Request:
     * {
     * "OrderId": 1,
     * "OrderDetailId": 1
     * }
     * <p>
     * Response:
     * {
     * "Message": "Trip started successfully",
     * "Success": true,
     * "Data": {
     * "TripId": 1,
     * "XPTSNumber": "XPTS/DEL/01",
     * "Status": "Trip Accepted",
     * "Delay": 0,
     * "AcceptedDate": "2020-09-12T17:26:16.59",
     * "Pickup1ArrivedDate": null,
     * "Pickup1LoadedDate": null,
     * "Pickup1DepartedDate": null,
     * "Pickup2ArrivedDate": null,
     * "Pickup2LoadedDate": null,
     * "Pickup2DepartedDate": null,
     * "Via1ArrivedDate": null,
     * "Via1UnloadedDate": null,
     * "Via1DepartedDate": null,
     * "Via2ArrivedDate": null,
     * "Via2UnloadedDate": null,
     * "Via2DepartedDate": null,
     * "DestinationArrivedDate": null,
     * "DestinationUnloadedDate": null,
     * "TripEndedDate": null,
     * "XPCNs": [
     * {
     * "XPCNId": 1,
     * "XPCNNumber": "XPCN/DEL/01",
     * "PODUrl": null,
     * "MPODUrl": null,
     * "MPODRemarks": null
     * }
     * ],
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

    private void ParsePOST() {
        RequestQueue mQueue = null;//Remove null when Implementing. Then add below Line.
        //mQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, "ENTER URL", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("Success");
                    if (success.equals("true")) {
                        JSONObject sTrip = jsonObject.getJSONObject("Data");
                        String Status = sTrip.getString("Status");
                    }
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
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("OrderId", "22");
                hashMap.put("OrderDetailId", "515");
                return hashMap;
            }

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

    // Login

    /**
     * LOGIN POST:
     * Request:
     * {
     * "Email": "customer@yopmail.com",
     * "Password": "Password@1"
     * }
     * <p>
     * Response:
     * {
     * "Message": "Logged in successfully",
     * "Success": true,
     * "Data": {
     * "UserId": "8UA5NzsdgRQ-3D-",
     * "FirstName": "Customer",
     * "LastName": "",
     * "Email": "customer@yopmail.com",
     * "Phone": "6541654654",
     * "AuthToken": "8hqkQkP5E-2B-iiDgPLU-2B-ZS7srKwAmvdn4T",
     * "UserType": "Customer",
     * "Role": "",
     * "IsAdmin": null,
     * "DateOfBirth": null,
     * "DateOfJoining": null,
     * "Permissions": null
     * }
     * }
     */

    private void LoginPOST() {
        StringRequest request;
        RequestQueue requestQueue = null;//Remove null when Implementing. Then add below Line.;
        //requestQueue = Volley.newRequestQueue(this);

        request = new StringRequest(Request.Method.POST, "ENTER URL", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("Success");
                    if (success.equals("true")) {
                        JSONObject userJson = jsonObject.getJSONObject("Data");
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
                Log.d(TAG, "onErrorResponse: ");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("Email", "input_phone.getText().toString()");
                hashMap.put("Password", "input_password.getText().toString()");
                return hashMap;
            }
        };
        requestQueue.add(request);
    }

}
