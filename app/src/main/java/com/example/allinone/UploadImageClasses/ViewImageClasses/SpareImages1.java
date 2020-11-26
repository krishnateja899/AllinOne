package com.example.allinone.UploadImageClasses.ViewImageClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SpareImages1 implements Serializable, Parcelable {
    private int id, spareId;
    private String name, path;

    public SpareImages1(int id, int spareId, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.spareId = spareId;
    }

    protected SpareImages1(Parcel in) {
        id = in.readInt();
        spareId = in.readInt();
        name = in.readString();
        path = in.readString();
    }

    public static final Creator<SpareImages1> CREATOR = new Creator<SpareImages1>() {
        @Override
        public SpareImages1 createFromParcel(Parcel in) {
            return new SpareImages1(in);
        }

        @Override
        public SpareImages1[] newArray(int size) {
            return new SpareImages1[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getSpareId() {
        return spareId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(spareId);
        dest.writeString(name);
        dest.writeString(path);
    }
}

