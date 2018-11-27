package com.example.d33p.onlinefood.Api;

import com.google.gson.annotations.SerializedName;

public class Retro {
    private String id;
    private String item;
    private String variant;
    private String inventory;
    private String price;

    @SerializedName("price*")
    private String priceC;

    public Retro(String id, String item, String variant, String inventory, String price, String priceC) {
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

    public String getItem() {
        return item;
    }

    public String getVariant() {
        return variant;
    }

    public String getInventory() {
        return inventory;
    }

    public String getPrice() {
        return price;
    }

    public String getPriceC() {
        return priceC;
    }
}