package com.example.d33p.onlinefood.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.d33p.onlinefood.cart.Cartitems;
import com.example.d33p.onlinefood.order.Orderitems;

import java.util.ArrayList;

public class SqliteDB extends SQLiteOpenHelper {


    public static final String db="foodItems.db";
    public static final String carttable="cart";
    public static final String ordertable="placeditems";
    public static final String orderidtable="orderid";
    public static final String orderitemtable="ordereditems";

    public static final String itemid="Id";
    public static final String itemname="Item";
    public static final String itemvariant="variant";
    public static final String iteminventory="inventory";
    public static final String itemprice="price";
    public static final String itemtrack="track";
    public static final String itemdeliver="deliver";
    public static final String oninventory="oninventory";

    public static final String order_id="order_id";

    public SqliteDB(Context context) {
        super(context, db, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL();
        db.execSQL("create table "+orderidtable+" (order_id text primary key)");
        db.execSQL("create table "+orderitemtable+" (order_id text references "+orderidtable+"(orderid),Item text,price integer,variant text,inventory text )");
        db.execSQL("create table "+carttable+" (Id text, Item text, variant text, inventory text,price integer,track text)");
        db.execSQL("create table "+ordertable+" (Id text, Item text, variant text, inventory text,price integer,track text, deliver text)");
        db.execSQL("create table "+oninventory+"(Id text, Item text, inventory text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + carttable);
        onCreate(db);
    }

    public void insdelcart(){
        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("insert into "+ordertable+"(Item,variant,price,track) select Item,variant,price,track from "+carttable);
        db.delete(carttable,null,null);
        //db.execSQL("create table "+carttable+" (Id text, Item text, variant text, inventory text,price integer,track text)");
    }

    public void insertorders(String orderid){
        SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues cv1=new ContentValues();
        cv1.put(order_id,orderid);
        db1.insert(orderidtable,null,cv1);
        //ArrayList<Orderitems> arrayList=new ArrayList<>();
        SQLiteDatabase dbr=this.getReadableDatabase();
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Cursor cursor=dbr.rawQuery("SELECT * FROM "+carttable,null);
        while(cursor.moveToNext()){
            String name=cursor.getString(1);
            String variant=cursor.getString(3);
            String price=cursor.getString(2);
            cv.put(order_id,orderid);
            cv.put(itemname,name);
            cv.put(itemvariant,variant);
            cv.put(itemprice,price);
            db.insert(orderitemtable,null,cv);
        }
    }

    public ArrayList<Orderitems> gettoorder(){
        ArrayList<Orderitems> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT order_id FROM "+orderidtable,null);
        while(cursor.moveToNext()){
            String orderid=cursor.getString(0);

            Orderitems order=new Orderitems(orderid);

            arrayList.add(order);
        }
        return arrayList;
    }
    public void insertcart(String name, String variant, int price, String track){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        //cv.put(itemid,id);
        cv.put(itemname,name);
        cv.put(itemvariant,variant);
        cv.put(itemprice,price);
        cv.put(itemtrack,track);
        db.insert(carttable,null,cv);
        /*if(res==-1){
            return false;
        }
        else{
            return true;
        }*/
    }
    //public void get
    public void insertorder(String name, String variant, int price, String track, String deliver){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        //cv.put(itemid,id);
        cv.put(itemname,name);
        cv.put(itemvariant,variant);
        cv.put(itemprice,price);
        cv.put(itemtrack,track);
        cv.put(itemdeliver,deliver);
        db.insert(ordertable,null,cv);
    }

    public ArrayList<Cartitems> getdata(){
        ArrayList<Cartitems> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+carttable,null);
        while(cursor.moveToNext()){
            String price=cursor.getString(4);
            String name=cursor.getString(1);
            String variant=cursor.getString(2);
            String track=cursor.getString(5);

            Cartitems cart=new Cartitems(name,variant,price,track);

            arrayList.add(cart);
        }
        return arrayList;
    }

    public ArrayList<Cartitems> getdataOrder(){
        ArrayList<Cartitems> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ordertable,null);
        while(cursor.moveToNext()){
            String price=cursor.getString(4);
            String name=cursor.getString(1);
            String variant=cursor.getString(2);
            String track=cursor.getString(5);
            String deliver=cursor.getString(6);
            Cartitems cart=new Cartitems(name,variant,price,track,deliver);

            arrayList.add(cart);
        }
        return arrayList;
    }
    public String getTotalofItems(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM("+itemprice+") FROM "+carttable, null);
        String total;
        if(cursor.moveToFirst()) {
            total = cursor.getString(0);
        } else {
            total = "0";
        }
        return total;
    }

    public String getTotalofOrders(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM("+itemprice+") FROM "+ordertable, null);
        String total;
        if(cursor.moveToFirst()) {
            total = cursor.getString(0);
        } else {
            total = "0";
        }
        return total;
    }



    /*public void delete(String track){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete(carttable,"track = ?",new String[] {track});
    }*/
    /*public void insertinventory(String id,String item,int inventory){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(itemid,id);
        cv.put(itemname,item);
        cv.put(iteminventory,inventory);
        db.insert(oninventory,null,cv);
    }*/
}
