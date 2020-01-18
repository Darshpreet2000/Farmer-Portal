package com.example.farmer_portal.Classes;

public class User {
    public String name,email,Phone,Industry,Area_Location;

    public User(){

    }

    public User(String name, String email, String phone,String area_Location) {
        this.name = name;
        this.email = email;
        Phone = phone;

        Area_Location = area_Location;
    }
}