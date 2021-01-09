package com.example.allinone.HelperClasses;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.allinone.Modals.PreferenceExampleModal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class AppPreferences {
    private static final String PREFERENCE_NAME = "AllInOne";
    private final SharedPreferences preference;
    private SharedPreferences.Editor editor;

    public AppPreferences(Context context) {
        preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = preference.edit();
        editor.apply();
    }

    //Preferences start

    //To check if the user has logged in:
    public boolean CheckLogin() {
        return preference.getString("LoginID", null) != null;
    }

    //To delete all the SharedPref on Logout:
    public void ClearPreferences() {
        editor = preference.edit();
        editor.clear();
        editor.apply();
    }

    //To delete only few items
    public void ClearPreferenceItems() {
        editor = preference.edit();
        editor.remove("Delay");
        editor.remove("CaseId");
        editor.remove("CaseNumber");
        editor.apply();
    }

    //To set and get one item
    public void setOneItem(String statusOfTrip) {
        editor.putString("StatusOfTrip", statusOfTrip);
        editor.apply();
    }

    public String getOneItem() {
        return preference.getString("StatusOfTrip", " ");
    }

    //To set and get multiple items using modal
    public void setMultipleItems(PreferenceExampleModal example) {
        editor = preference.edit();
        editor.putString("FirstName", example.getFirstName());
        editor.putString("LastName", example.getLastName());
        editor.putString("Address", example.getAddress());
        editor.apply();
    }

    public PreferenceExampleModal getMultipleItems() {
        return new PreferenceExampleModal(
                preference.getString("FirstName", ""),
                preference.getString("LastName", ""),
                preference.getString("Address", "")
        );
    }

    //or
    public PreferenceExampleModal getMultipleItems1() {
        PreferenceExampleModal obj = new PreferenceExampleModal();
        obj.setFirstName(preference.getString("FirstName", ""));
        obj.setLastName(preference.getString("LastName", ""));
        obj.setAddress(preference.getString("Address", ""));
        return obj;
    }

    //To set and get in JSONObject
    public void setJSON(JSONObject object) {
        Gson gson = new Gson();
        String list = gson.toJson(object);
        editor.putString("JSON", list);
        editor.apply();
    }

    public JSONObject getJSON() {
        Gson gson = new Gson();
        String list = preference.getString("JSON", "N/A");
        Type type = new TypeToken<JSONObject>() {
        }.getType();
        return gson.fromJson(list, type);
    }

    //Store Image in Base69. Check the SharedPref class on how to convert a Bitmap.
    public void setProfileImage(String img) {
        editor.putString("ProfileImage", img);
        editor.apply();
    }

    public String getProfileImage() {
        return preference.getString("ProfileImage", " ");
    }
}
