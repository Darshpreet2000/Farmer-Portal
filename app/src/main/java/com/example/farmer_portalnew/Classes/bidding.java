package com.example.farmer_portalnew.Classes;

public class bidding {
    public String bidId,buyerId,cropId;

    public bidding() {
    }

    public bidding(String bidId, String buyerId, String cropId) {
        this.bidId = bidId;
        this.buyerId = buyerId;
        this.cropId = cropId;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
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
}
