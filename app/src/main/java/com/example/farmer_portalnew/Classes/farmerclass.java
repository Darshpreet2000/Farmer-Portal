package com.example.farmer_portalnew.Classes;

import java.io.Serializable;

public class farmerclass implements Serializable {
    String  actualPrice,cropId,cropName,cropOwner,offerQuantity,offerPrice,buyerid;

    public farmerclass() {
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
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

    public String getOfferQuantity() {
        return offerQuantity;
    }

    public void setOfferQuantity(String offerQuantity) {
        this.offerQuantity = offerQuantity;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public farmerclass(String actualPrice, String cropId, String cropName, String cropOwner, String offerQuantity, String offerPrice) {
        this.actualPrice = actualPrice;
        this.cropId = cropId;
        this.cropName = cropName;
        this.cropOwner = cropOwner;
        this.offerQuantity = offerQuantity;
        this.offerPrice = offerPrice;
    }
}
