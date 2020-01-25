package com.example.farmer_portalnew.Classes;

public class bidclass {
    String  cropName,minQuantity,price,quantity,category,name,phone;

    public bidclass() {
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public bidclass(String cropName, String minQuantity, String price, String quantity, String category, String name, String phone) {
        this.cropName = cropName;
        this.minQuantity = minQuantity;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.name = name;
        this.phone = phone;
    }
}
