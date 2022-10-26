package com.example.demo.Product;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
public class Product {

    @Id
    private Long pid;
    private String pname;
    private Long price;
    private String description;
    private String spec;
    private Long catid;
    private String image;
    private float rate;
    private float discount;
    private Date adddate;
    private boolean active;

    public Product() {
    }

    public Product(Long pid, String pname, Long price, String description, String spec, Long catid, String image, float rate, float discount, Date adddate, boolean active) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.description = description;
        this.spec = spec;
        this.catid = catid;
        this.image = image;
        this.rate = rate;
        this.discount = discount;
        this.adddate = adddate;
        this.active = active;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Long getCatid() {
        return catid;
    }

    public void setCatid(Long catid) {
        this.catid = catid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Object[] toArray()
    {
        return new Object[]{this.pid, this.pname, this.price, this.description, this.spec, this.catid, this.image, this.rate, this.discount, this.adddate, this.active};
    }

    public Object[] toArrayForUpdate()
    {
        return new Object[]{this.pname, this.price, this.description, this.spec, this.catid, this.image, this.rate, this.discount, this.adddate, this.active, this.pid};
    }
}
