package com.example.d33p.onlinefood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class SqliteDB extends SQLiteOpenHelper {


    public static final String db="foodItems.db";
    public static final String tablename="Items";
    public static final String tablenamecart="CartItems";

    public static final String itemid="Id";
    public static final String itemname="Item";
    public static final String itemvariant="variant";
    public static final String iteminventory="inventory";
    public static final String itemprice="price";
    public static final String itemtrack="track";
    public static final String itemdeliver="deliver";

    public SqliteDB(Context context) {
        super(context, db, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tablename+" (Id text, Item text, variant text, inventory text,price text,track text)");
        db.execSQL("create table "+tablenamecart+" (Item text, variant text, inventory text,price text,track text, deliver text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablename);
        onCreate(db);
    }
    public void insert(String id, String name, String variant, String price, String track){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(itemid,id);
        cv.put(itemname,name);
        cv.put(itemvariant,variant);
        cv.put(itemprice,price);
        cv.put(itemtrack,track);
        db.insert(tablename,null,cv);
        /*if(res==-1){
            return false;
        }
        else{
            return true;
        }*/
    }
    public void insertcart(String name, String variant, String price, String track, String deliver){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        //cv.put(itemid,id);
        cv.put(itemname,name);
        cv.put(itemvariant,variant);
        cv.put(itemprice,price);
        cv.put(itemtrack,track);
        cv.put(itemdeliver,deliver);
        db.insert(tablenamecart,null,cv);
    }
    public ArrayList<Cartitems> getdata(){
        ArrayList<Cartitems> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+tablename,null);
        while(cursor.moveToNext()){
            String price=cursor.getString(4);
            String name=cursor.getString(1);
            String variant=cursor.getString(2);

            Cartitems cart=new Cartitems(name,variant,price);

            arrayList.add(cart);
        }
        return arrayList;
    }
    public ArrayList<Cartitems> getdataOrder(){
        ArrayList<Cartitems> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+tablenamecart,null);
        while(cursor.moveToNext()){
            String price=cursor.getString(3);
            String name=cursor.getString(0);
            String variant=cursor.getString(1);
            String track=cursor.getString(4);
            String deliver=cursor.getString(5);
            Cartitems cart=new Cartitems(name,variant,price,track,deliver);

            arrayList.add(cart);
        }
        return arrayList;
    }
    public void delete(String track){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete(tablename,"track = ?",new String[] {track});
    }
}
