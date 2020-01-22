package com.example.farmer_portal.Classes;

public class bidding {
   public String farmerid,price,name,quantity,buyerid;

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public bidding() {
    }

    public bidding(String farmerid, String price, String name, String quantity,String buyerid) {
        this.farmerid = farmerid;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
        this.buyerid=buyerid;
    }

    public String getFarmerid() {
        return farmerid;
    }

    public void setFarmerid(String farmerid) {
        this.farmerid = farmerid;
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


}
