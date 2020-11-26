package com.example.allinone.UploadImageClasses.ViewImageClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class RepairsImageDao1 implements Serializable {
    int repairid;
    String repairname, remarks;
    ArrayList<SpareImages1> images;


    public RepairsImageDao1(int repairid, String imagePath, String remarks, ArrayList<SpareImages1> images) {
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

    public ArrayList<SpareImages1> getImages() {
        return images;
    }
}
