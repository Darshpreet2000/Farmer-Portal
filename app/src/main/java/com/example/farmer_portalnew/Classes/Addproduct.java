package com.example.farmer_portalnew.Classes;

import java.io.Serializable;

public class Addproduct implements Serializable {

    private String name,quantity,category,farmerid,cropid,bid,farmername,farmerphone,userid;
    private  int CropPrice;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFarmername() {
        return farmername;
    }

    public void setFarmername(String farmername) {
        this.farmername = farmername;
    }

    public String getFarmerphone() {
        return farmerphone;
    }

    public void setFarmerphone(String farmerphone) {
        this.farmerphone = farmerphone;
    }

    public Addproduct() {

    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getCropid() {
        return cropid;
    }

    public void setCropid(String cropid) {
        this.cropid = cropid;
    }

    public String getFarmerid() {
        return farmerid;
    }

    public void setFarmerid(String farmerid) {
        this.farmerid = farmerid;
    }


    public Addproduct(String name, String quantity, String category, String farmerid,  int cropPrice) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.farmerid = farmerid;
        CropPrice = cropPrice;
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
