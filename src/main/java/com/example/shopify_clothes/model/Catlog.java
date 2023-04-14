package com.example.shopify_clothes.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Catlog {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long catlogID;
    private String catlogName;
    private String catlogDescription;
    private String category;
    private Double price;
    private String size;
    private boolean featured;

    private int qty;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    private String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCatlogID() {
        return catlogID;
    }

    public void setCatlogID(Long catlogID) {
        this.catlogID = catlogID;
    }

    public String getCatlogName() {
        return catlogName;
    }

    public void setCatlogName(String catlogName) {
        this.catlogName = catlogName;
    }

    public String getCatlogDescription() {
        return catlogDescription;
    }

    public void setCatlogDescription(String catlogDescription) {
        this.catlogDescription = catlogDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
}
