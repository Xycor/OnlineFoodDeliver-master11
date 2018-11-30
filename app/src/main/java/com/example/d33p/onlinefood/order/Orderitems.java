package com.example.d33p.onlinefood.order;

public class Orderitems {
    private String id;
    private String item;
    private String variant;
    private String inventory;
    private String price;
    private String track;
    private String deliver;
    private String totalitems;
    private String ordertime;

    public Orderitems(String item, String variant, String price, String track) {
        this.item = item;
        this.variant = variant;
        this.price = price;
        this.track=track;

    }
    public Orderitems(String id,String totalitems,String deliver,String ordertime,String track) {
        this.id = id;
        this.totalitems=totalitems;
        this.deliver=deliver;
        this.ordertime=ordertime;
    }
    /*public Orderitems(String item, String variant, String price, String track, String deliver) {
        this.item = item;
        this.variant = variant;
        this.price = price;
        this.track=track;
        this.deliver=deliver;
    }*/
    /*public Orderitems(String id,String item, String variant, String price, String track, String deliver) {
        this.id = id;
        this.item = item;
        this.variant = variant;
        this.price = price;
        this.track=track;
        this.deliver=deliver;
    }*/

    public Orderitems(String item, String price,String inventory,String variant,String abc,String cde) {

        //this.id = id;
        this.item = item;
        this.price = price;
        this.inventory = inventory;
        this.variant=variant;
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

    public String getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(String totalitems) {
        this.totalitems = totalitems;
    }
    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

}



