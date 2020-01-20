package com.example.farmer_portal.Classes;

public class User {
    public String name,email,Phone,Industry,Area_Location;
    public boolean phonenumbervarified;

    public User(){

    }

    public User(String name, String email, String phone,String area_Location,boolean  phonenumbervarified) {
        this.name = name;
        this.email = email;
        Phone = phone;
        this.phonenumbervarified=phonenumbervarified;

        Area_Location = area_Location;
    }


}