package com.example.farmer_portalnew.Classes;

public class crops {
   public String cropId,cropOwner;

    public crops(String cropId, String cropOwner) {
        this.cropId = cropId;
        this.cropOwner = cropOwner;
    }

    public crops() {
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }

    public String getCropOwner() {
        return cropOwner;
    }

    public void setCropOwner(String cropOwner) {
        this.cropOwner = cropOwner;
    }
}
