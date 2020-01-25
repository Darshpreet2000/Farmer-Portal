package com.example.farmer_portalnew.Classes;

import java.io.Serializable;

public class TransportDetail implements Serializable {
    private  String pickLoc;
    private  String dropLoc;
   private  String price;
   private  String queryOwnwe;

   public  TransportDetail(){

   }

    public TransportDetail(String pickLoc, String dropLoc, String price) {
        this.pickLoc = pickLoc;
        this.dropLoc = dropLoc;
        this.price = price;
    }

    public String getPickLoc() {
        return pickLoc;
    }

    public void setPickLoc(String pickLoc) {
        this.pickLoc = pickLoc;
    }

    public String getDropLoc() {
        return dropLoc;
    }

    public void setDropLoc(String dropLoc) {
        this.dropLoc = dropLoc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQueryOwnwe() {
        return queryOwnwe;
    }

    public void setQueryOwnwe(String queryOwnwe) {
        this.queryOwnwe = queryOwnwe;
    }
}
