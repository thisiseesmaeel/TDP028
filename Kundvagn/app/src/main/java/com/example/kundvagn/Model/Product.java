package com.example.kundvagn.Model;

import com.google.firebase.firestore.DocumentId;

public class Product {

    @DocumentId
    String productid;
    String title, imageUrl, desc;
    int price, quantity;

    public Product() {
    }

    public Product(String productid, String title, String imageUrl, String desc, int price, int quantity) {
        this.productid = productid;
        this.title = title;
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
