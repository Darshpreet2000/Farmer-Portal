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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getIndustry() {
        return Industry;
    }

    public String getArea_Location() {
        return Area_Location;
    }

    public boolean isPhonenumbervarified() {
        return phonenumbervarified;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }

    public void setArea_Location(String area_Location) {
        Area_Location = area_Location;
    }

    public void setPhonenumbervarified(boolean phonenumbervarified) {
        this.phonenumbervarified = phonenumbervarified;
    }
}