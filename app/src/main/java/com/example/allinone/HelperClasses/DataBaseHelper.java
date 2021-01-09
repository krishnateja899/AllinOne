package com.example.allinone.HelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.allinone.Modals.DataBaseExampleModal;

import java.io.ByteArrayOutputStream;

import static android.content.ContentValues.TAG;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "AllInOne.db";
    private static final int VERSION = 1;

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInByte;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void TruncateAllTablesOnLogout() {

    }

    /**
     * Table Querying Start
     */

    //Store data
    public boolean AddData(DataBaseExampleModal tableFiveModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("COLETA_ID", tableFiveModel.getColetaID());
        contentValues.put("STREET_NAME", tableFiveModel.getStreetName());
        contentValues.put("APT_NO", tableFiveModel.getAptNo());
        contentValues.put("CITY", tableFiveModel.getCity());
        contentValues.put("STATE", tableFiveModel.getState());
        contentValues.put("PINCODE", tableFiveModel.getPincode());
        contentValues.put("TICK_MARK", "false");

        long result = db.insert("TABLE_SCANNER_DETAILS", null, contentValues);
        db.close();
        return result != -1;
        //How to Add Data
        //boolean check = mDataBaseHelper.AddData("515151", "Prakash", "511", "Hyd", "Tel", "500051");
        //System.out.println(check);
    }

    //Store image
    public void addDataToTableThree(Bitmap image) {
        SQLiteDatabase db = this.getWritableDatabase();
        //While creating table for storing Images you should use "BLOB"
        //Bitmap imageToStoreBitmap = deliveryModal.getImage();
        objectByteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
        imageInByte = objectByteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("DELIVERY_IMAGE", imageInByte);

        long result = db.insert("TABLE_DELIVERY_DETAILS", null, contentValues);
        if (result == -1) {
            Log.e(TAG, "storeDeliveryDetails: " + result);
        } else {
            Log.e(TAG, "storeDeliveryDetails1: " + result);
        }
        db.close();
    }

    //For getting the data from table
    public Cursor GetData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + "TABLE_DELIVERY_DETAILS";
        return db.rawQuery(query, null);
    }

    //For getting int value
    public int DashSyncCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + "TABLE_TOTAL_LIST_DETAILS" + " WHERE " + "TICK_MARK" + " = 'true' ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    //For getting string value
    public String CheckClientNumber(String cli_num) {
        String text = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + "HAWB_CODE" + " FROM " + "TABLE_HAWB_CODES" + " WHERE " + "CLIENT_NUMBER" + " = '" + cli_num + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                text = cursor.getString(cursor.getColumnIndex("HAWB_CODE"));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return text;
    }

    //For getting boolean value
    public boolean CheckCode(String cod) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + "TABLE_HAWB_CODES" + " WHERE " + "HAWB_CODE" + " = '" + cod + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

}
