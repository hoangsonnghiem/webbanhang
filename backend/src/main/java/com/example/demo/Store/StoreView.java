package com.example.demo.Store;

public class StoreView {
    private Long storeid;
    private String city;
    private String district;
    private String adminname;
    private boolean active;

    public StoreView() {
    }

    public StoreView(Long storeid, String city, String district, String adminname, boolean active) {
        this.storeid = storeid;
        this.city = city;
        this.district = district;
        this.adminname = adminname;
        this.active = active;
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
