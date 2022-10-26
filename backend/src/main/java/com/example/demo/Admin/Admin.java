package com.example.demo.Admin;

public class Admin {
    private String email;
    private int role;

    public Admin() {
    }

    public Admin(String email, int role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
