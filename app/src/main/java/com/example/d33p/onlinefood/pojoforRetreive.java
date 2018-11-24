package com.example.d33p.onlinefood;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class pojoforRetreive {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("itemname")
    @Expose
    private String itemname;
    @SerializedName("price")
    @Expose
    private String price;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {

        this.itemname = itemname;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
