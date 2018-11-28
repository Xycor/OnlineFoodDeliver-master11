package com.example.d33p.onlinefood.order;

public class Orderitems {
    private String id,item,variant,inventory,price,track,deliver;

    public Orderitems(String item, String variant, String price,String track) {
        this.item = item;
        this.variant = variant;
        this.price = price;
        this.track=track;
    }
    public Orderitems(String id) {
        this.id = id;
    }
    public Orderitems(String item, String variant, String price, String track, String deliver) {
        this.item = item;
        this.variant = variant;
        this.price = price;
        this.track=track;
        this.deliver=deliver;
    }
    public Orderitems(String id,String item, String variant, String price, String track, String deliver) {
        this.id = id;
        this.item = item;
        this.variant = variant;
        this.price = price;
        this.track=track;
        this.deliver=deliver;
    }

    public Orderitems(String item, String price) {
        //this.id = id;
        this.item = item;
        //this.variant = variant;
        this.price = price;
        //this.track=track;
        //this.deliver=deliver;
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

