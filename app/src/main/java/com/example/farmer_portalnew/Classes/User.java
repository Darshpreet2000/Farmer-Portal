package com.example.farmer_portalnew.Classes;

import java.io.Serializable;

public class User implements Serializable    {
    private String address,email,name,password,phoneNo,userType,UserLanguage;
 //   private String name,email,phone,area_Location,RegisterAs;
    public User(){

    }

    public User(String address, String email, String name, String password, String phoneNo, String userType,String UserLanguage) {
        this.address = address;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
        this.userType = userType;
        this.UserLanguage=UserLanguage;
    }

    public String getUserLanguage() {
        return UserLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        UserLanguage = userLanguage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}