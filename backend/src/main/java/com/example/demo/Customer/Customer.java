package com.example.demo.Customer;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class Customer {
    
    private String email;
    private String name;
    private Date dob;
    private int gender;
    private int phone;
    private String address;
    private String district;
    private String city;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password not in correct form")
    private String pass;
    private int active;

    public Customer() {
    }

    public Customer(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public Customer(String email, String name, Date dob, int gender, int phone, String address, String district, String city, String pass, int active) {
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.district = district;
        this.city = city;
        this.pass = pass;
        this.active = active;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int isGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Object[] toArray()
    {
        return new Object[]{this.email, this.name, this.dob, this.gender, this.phone, this.address, this.district, this.city, this.pass, this.active};
    }

    public Object[] toArrayForUpdate()
    {
        return new Object[]{this.name, this.dob, this.gender, this.phone, this.address, this.district, this.city, this.active, this.email};
    }

    public Object[] toArrayForPasswordChange()
    {
        return new Object[]{this.pass, this.email};
    }
}
