package com.example.farmer_portal.Classes;

import java.io.Serializable;

public class User implements Serializable {
    public String name,email,phone,area_Location;
    public User(){

    }

    public User(String name, String email, String phone, String area_Location) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.area_Location = area_Location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea_Location() {
        return area_Location;
    }

    public void setArea_Location(String area_Location) {
        this.area_Location = area_Location;
    }
}