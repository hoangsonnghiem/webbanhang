package com.example.demo.Store;

public class Store {
    private Long storeid;
    private String city;
    private String district;
    private String email;
    private boolean active;

    public Store() {
    }

    public Store(Long storeid, String city, String district, String email, boolean active) {
        this.storeid = storeid;
        this.city = city;
        this.district = district;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Object[] toArray()
    {
        return new Object[]{this.storeid, this.city, this.district, this.email};
    }

    public Object[] toArrayForUpdate()
    {
        return new Object[]{this.city, this.district, this.email, this.storeid};
    }
}
