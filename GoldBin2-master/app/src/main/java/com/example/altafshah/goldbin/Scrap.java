package com.example.altafshah.goldbin;

public class Scrap {
    private String title,pricePU, measure, quantity;
    //genre, year;

    public Scrap() {
    }

    public Scrap(String title, String pricePU, String measure, String quantity) {
        this.title = title;
        this.pricePU = pricePU;
        this.measure = measure;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPricePU() {
        return pricePU;
    }

    public void setPricePU(String pricePU) {
        this.pricePU = pricePU;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}