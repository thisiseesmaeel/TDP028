package com.example.kundvagn.Model;

import com.google.firebase.firestore.DocumentId;

public class Product {

    @DocumentId
    String productid;
    String rubrik, bildUrl;
    int pris, antal;

    public Product() {
    }

    public Product(String productid, String rubrik, String bildUrl, int pris, int antal) {
        this.productid = productid;
        this.rubrik = rubrik;
        this.bildUrl = bildUrl;
        this.pris = pris;
        this.antal = antal;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getRubrik() {
        return rubrik;
    }

    public void setRubrik(String rubrik) {
        this.rubrik = rubrik;
    }

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }
}
