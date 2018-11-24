package com.example.d33p.onlinefood;

import com.google.gson.annotations.SerializedName;

public class ProductItem {
    private String id;
    private String item;
    private String variant;
    private String inventory;
    private String price;

    @SerializedName("price*")
    private String priceC;

    public ProductItem(String id, String item, String variant, String inventory, String price, String priceC) {
        this.id = id;
        this.item = item;
        this.variant = variant;
        this.inventory = inventory;
        this.price = price;
        this.priceC = priceC;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceC() {
        return priceC;
    }

    public void setPriceC(String priceC) {
        this.priceC = priceC;
    }
}
