package com.example.allinone.UploadImageClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class RepairsImageDao implements Serializable {
    int repairid;
    String repairname,remarks;
    ArrayList<SpareImages> images;


    public RepairsImageDao(int repairid, String imagePath, String remarks, ArrayList<SpareImages> images) {
        this.repairid = repairid;
        this.repairname = imagePath;
        this.remarks = remarks;
        this.images = images;
    }

    public int getRepairid() {
        return repairid;
    }

    public String getRepairname() {
        return repairname;
    }

    public String getRemarks() {
        return remarks;
    }

    public ArrayList<SpareImages> getImages() {
        return images;
    }
}
