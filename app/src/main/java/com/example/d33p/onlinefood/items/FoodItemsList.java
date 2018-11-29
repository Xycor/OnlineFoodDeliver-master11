package com.example.d33p.onlinefood.items;

public class FoodItemsList {
    private String id,item,variant,track,deliver;
    private int inventory,price,pricec;

    public FoodItemsList(String item, String variant, int price, String track) {
        this.item = item;
        this.variant = variant;
        this.price = price;
        this.track=track;
    }

    public FoodItemsList(String id, String item, String variant, int inventory, int price, int pricec) {
        this.id=id;
        this.item = item;
        this.variant = variant;
        this.inventory=inventory;
        this.price = price;
        this.pricec=pricec;
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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPricec() {
        return pricec;
    }

    public void setPricec(int pricec) {
        this.pricec = pricec;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }
}
