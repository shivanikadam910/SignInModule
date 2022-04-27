package model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String fName, lName, address, country;
    private String email;
    private int zipcode, phone;

    public UserModel(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public static UserModel getLoginUser(){
        UserModel user = new UserModel("shivani", "kadam");
//        user.setfName("Shivani");
//        user.setlName("Kadam");
        user.setAddress("Surat");
        user.setCountry("India");
        user.setZipcode(360005);
        user.setEmail("shivanikadam910@gmail.com");
        user.setPhone(720106380);
        return user;
    }
}