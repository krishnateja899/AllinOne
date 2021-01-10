package com.example.allinone.RecyclerViews.HEighthRecView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.allinone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Delayed_Trips_Fragment extends Fragment {
    private ArrayList<AssignFragData> active_alerts_lists;
    private Trips_Adapter adapter;
    private RecyclerView assigned_recycler_vw;
    private RequestQueue mQueue;
    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_delayed__trips_, container, false);
        init(v);

        return v;
    }

    private void init(View v) {
        mQueue = Volley.newRequestQueue(getContext());
        active_alerts_lists = new ArrayList<>();
        assigned_recycler_vw = (RecyclerView) v.findViewById(R.id.assigned_recycler_vw);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        assigned_recycler_vw.setLayoutManager(layoutManager);
        JsonParse();
    }

    private void JsonParse() {

        String url = "http://10.0.2.2:3000/Start";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("TAG", "DelayedTripFrag: " + response);
                try {
                    JSONObject object = response.getJSONObject("Data");
                    JSONArray array1 = object.getJSONArray("TotalTrips");
                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject data = array1.getJSONObject(i);
                        String status = data.getString("Status");
                        if (status.equals("Delayed")) {
                            JSONArray array5 = data.getJSONArray("XPCNs");
                            for (int j = 0; j < array5.length(); j++) {
                                //JSONObject data2 = array5.getJSONObject(i);
                                // fragData1.setXpcn(data2.getString("XPCNNumber"));
                            }
                            AssignFragData fragData = new AssignFragData(
                                    data.getString("OrderNumber"),
                                    data.getString("OrderNumber"),//xpcnnumber
                                    data.getString("XPTSNumber"),
                                    data.getString("FFVName"),
                                    data.getString("VehicleNumber"),
                                    data.getString("Origin"),
                                    data.getString("Pickup1"),
                                    data.getString("OriginDepartedDate"),
                                    data.getString("DestinationArrivedDate"),
                                    data.getString("TripEndedDate"),
                                    data.getString("TransitTime") + " hrs",
                                    data.getString("TimeTaken"),
                                    data.getString("HaltingHours"),
                                    data.getString("Status"),
                                    data.getString("Destination"),
                                    data.getString("TripId")
                            );
                            active_alerts_lists.add(fragData);
                            adapter = new Trips_Adapter(active_alerts_lists, getActivity());
                            assigned_recycler_vw.setAdapter(adapter);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
    }

}