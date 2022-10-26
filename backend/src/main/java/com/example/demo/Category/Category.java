package com.example.demo.Category;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    private Long catid;
    private String catname;

    public Category() {
    }

    public Category(Long catid, String catname) {
        this.catid = catid;
        this.catname = catname;
    }

    public Long getCatid() {
        return catid;
    }

    public void setCatid(Long catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }
}
