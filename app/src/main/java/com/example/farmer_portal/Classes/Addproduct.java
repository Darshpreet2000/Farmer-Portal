package com.example.farmer_portal.Classes;

import android.net.wifi.aware.PublishConfig;

import java.io.Serializable;

public class Addproduct implements Serializable {

    private String name,quantity,category;
    private   int CropPrice;

    public Addproduct() {

    }

    public Addproduct(String name, String quantity, String category,int CropPrice) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.CropPrice=CropPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCropPrice() {
        return CropPrice;
    }

    public void setCropPrice(int cropPrice) {
        CropPrice = cropPrice;
    }
}
