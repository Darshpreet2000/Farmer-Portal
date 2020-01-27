package com.example.farmer_portalnew.Classes;

public class Originalcart {

    String buyerId,cropId,cropName,cropOwner,price,transactionStatus,category,buyQuantity,availableQuantity,mykey;

    public String getMykey() {
        return mykey;
    }

    public void setMykey(String mykey) {
        this.mykey = mykey;
    }

    public Originalcart() {
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(String buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Originalcart(String buyerId, String cropId, String cropName, String cropOwner, String price, String transactionStatus, String category, String buyQuantity, String availableQuantity) {
        this.buyerId = buyerId;
        this.cropId = cropId;
        this.cropName = cropName;
        this.cropOwner = cropOwner;
        this.price = price;
        this.transactionStatus = transactionStatus;
        this.category = category;
        this.buyQuantity = buyQuantity;
        this.availableQuantity = availableQuantity;
    }
}
