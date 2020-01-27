package com.example.farmer_portalnew.Classes;

import java.io.Serializable;

public class Cart implements Serializable {
    String buyQuantity;
    String cropName;
    String cropOwner;
    String minQuantity;
    String price;
    String quantity;
    String category;
    String cropid;

    public Cart() {
    }

    public String getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(String buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropOwner() {
        return cropOwner;
    }

    public void setCropOwner(String cropOwner) {
        this.cropOwner = cropOwner;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getCropid() {
        return cropid;
    }

    public void setCropid(String cropid) {
        this.cropid = cropid;
    }

    public Cart(String buyQuantity, String cropName, String cropOwner, String minQuantity, String price, String quantity, String category, String cropid) {
        this.buyQuantity = buyQuantity;
        this.cropName = cropName;
        this.cropOwner = cropOwner;
        this.minQuantity = minQuantity;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.cropid = cropid;
    }
}
