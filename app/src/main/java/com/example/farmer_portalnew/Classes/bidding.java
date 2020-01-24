package com.example.farmer_portalnew.Classes;

public class bidding {
    public String bidderid, price,farmerid,cropid;

    public bidding() {
    }

    public String getBidderid() {
        return bidderid;
    }

    public void setBidderid(String bidderid) {
        this.bidderid = bidderid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFarmerid() {
        return farmerid;
    }

    public void setFarmerid(String farmerid) {
        this.farmerid = farmerid;
    }

    public String getCropid() {
        return cropid;
    }

    public void setCropid(String cropid) {
        this.cropid = cropid;
    }

    public bidding(String bidderid, String price, String farmerid, String cropid) {
        this.bidderid = bidderid;
        this.price = price;
        this.farmerid = farmerid;
        this.cropid = cropid;
    }
}
