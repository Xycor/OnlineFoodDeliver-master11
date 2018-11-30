package com.example.d33p.onlinefood.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.d33p.onlinefood.cart.Cartitems;
import com.example.d33p.onlinefood.items.FoodItemsList;
import com.example.d33p.onlinefood.order.Orderitems;

import java.util.ArrayList;

public class SqliteDB extends SQLiteOpenHelper {


    public static final String db="foodItems.db";
    public static final String carttable="cart";
    public static final String ordertable="placeditems";
    public static final String orderidtable="orderid";
    public static final String orderitemtable="ordereditems";
    public static final String orderitemdistincttable="ordereditemsdistinct";

    public static final String itemid="Id";
    public static final String itemname="Item";
    public static final String ordertime="time";
    public static final String itemvariant="variant";
    public static final String iteminventory="inventory";
    public static final String itemprice="price";
    public static final String itempricec="pricec";
    public static final String itemtrack="track";
    public static final String itemdeliver="deliver";
    public static final String oninventory="oninventory";
    public static final String noOfItems="noOfItems";

    public static final String order_id="order_id";

    public SqliteDB(Context context) {
        super(context, db, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL();
        db.execSQL("create table "+orderidtable+" (order_id text primary key,noOfItems text,deliver text,time text)");
        db.execSQL("create table "+orderitemtable+" (order_id text references "+orderidtable+"(orderid),Item text,price integer,variant text,inventory text )");
        db.execSQL("create table "+carttable+" (Id text, Item text, variant text, inventory text,price integer,track text)");
        db.execSQL("create table "+ordertable+" (Id text, Item text, variant text, inventory text,price integer,track text, deliver text)");
        db.execSQL("create table "+orderitemdistincttable+" (order_id text references "+orderidtable+"(orderid),Item text,price integer,variant text,inventory text )");
        db.execSQL("create table "+oninventory+"(Id text, Item text,variant text, inventory integer,price integer,pricec integer)");
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

    public void insertorders(String orderid,String deliver, String time){
        SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues cv1=new ContentValues();
        cv1.put(order_id,orderid);
        cv1.put(itemdeliver,deliver);
        cv1.put(ordertime,time);
        db1.insert(orderidtable,null,cv1);
        //ArrayList<Orderitems> arrayList=new ArrayList<>();
        SQLiteDatabase dbr=this.getReadableDatabase();
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Cursor cursor=dbr.rawQuery("SELECT * FROM "+carttable,null);
        while(cursor.moveToNext()){
            String name=cursor.getString(1);
            String price=cursor.getString(4);
            String variant=cursor.getString(2);
            cv.put(order_id,orderid);
            cv.put(itemname,name);
            cv.put(itemprice,price);
            cv.put(itemvariant,variant);
            db.insert(orderitemtable,null,cv);
        }
        String noitems=getnoofitems(orderid);
        db.execSQL("update "+orderidtable+" set noOfItems='"+noitems+"' where order_id='"+orderid+"'");
    }
    public String getnoofitems(String orderid){
        SQLiteDatabase db=this.getReadableDatabase();
        String noitems="";
        Cursor cursor=db.rawQuery("select count(Item) from "+orderitemtable+" where order_id='"+orderid+"'",null);
        if(cursor.moveToFirst()) {
            noitems = cursor.getString(0);
        } else {
            noitems = "0";
        }
        return noitems;
    }

    public ArrayList<Orderitems> gettoorder(){
        ArrayList<Orderitems> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+orderidtable+" order by order_id desc",null);
        while(cursor.moveToNext()){
            String orderid=cursor.getString(0);
            String noitems=cursor.getString(1);
            String deliver=cursor.getString(2);
            String timeOrder=cursor.getString(3);

            Orderitems order=new Orderitems(orderid,noitems,deliver,timeOrder,"");

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

    public ArrayList<Orderitems> getItemsinOrder(String order_id){
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Orderitems> arrayList=new ArrayList<>();
        Orderitems order;
        Cursor cursor=db.rawQuery("SELECT distinct(Item),price,inventory,variant FROM ordereditemsdistinct inner join orderid on \n" +
                "                orderid.order_id=ordereditemsdistinct.order_id where ordereditemsdistinct.order_id='"+order_id+"'",null);

        while(cursor.moveToNext()){
            String item=cursor.getString(0);
            String price=cursor.getString(1);
            String invent=cursor.getString(2);
            String variant=cursor.getString(3);
            if(Integer.parseInt(invent)>1){
                order=new Orderitems(item,price,invent,variant+" each","","");
            }
            else{
                order=new Orderitems(item,price,invent,variant,"","");
            }
            arrayList.add(order);
        }
        return arrayList;
    }


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

    public String checkinventory(){
        SQLiteDatabase db=this.getReadableDatabase();
        String s;
        Cursor cursor=db.rawQuery("select count(Id) from "+oninventory,null);
        if(cursor.moveToFirst()) {
            s = cursor.getString(0);
        }
        else{
            s="0";
        }
        return s;
    }
    public void insertinventory(String id,String item,String variant,int inventory,int price,int pricec){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(itemid,id);
        cv.put(itemname,item);
        cv.put(itemvariant,variant);
        cv.put(iteminventory,inventory);
        cv.put(itemprice,price);
        cv.put(itempricec,pricec);
        db.insert(oninventory,null,cv);
    }
    public ArrayList<FoodItemsList> getdatalistitems(){
        ArrayList<FoodItemsList> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+oninventory,null);
        while(cursor.moveToNext()){
            String idd=cursor.getString(0);
            String name=cursor.getString(1);
            String variant=cursor.getString(2);
            int invent=cursor.getInt(3);
            int price=cursor.getInt(4);
            int pricec=cursor.getInt(5);
            FoodItemsList items=new FoodItemsList(idd,name,variant,invent,price,pricec);

            arrayList.add(items);
        }
        return arrayList;
    }

    public void operateinventory(String id,int i){
        SQLiteDatabase db=this.getWritableDatabase();
        SQLiteDatabase db1=this.getReadableDatabase();
        int s;
        Cursor cursor=db.rawQuery("select inventory from "+oninventory+" where Id='"+id+"'",null);
        if(cursor.moveToFirst()) {
            s = cursor.getInt(0);
        }
        else{
            s=0;
        }
        db.execSQL("update oninventory set inventory="+(s-i)+" where Id='"+id+"'");
    }
    public String checkcart(){
        SQLiteDatabase db=this.getReadableDatabase();
        String s;
        Cursor cursor=db.rawQuery("select count(Item) from "+carttable,null);
        if(cursor.moveToFirst()) {
            s = cursor.getString(0);
        }
        else{
            s="0";
        }
        return s;
    }
    public void checkdistinctitems(String orderid){
        SQLiteDatabase db=this.getReadableDatabase();
        SQLiteDatabase db1=this.getWritableDatabase();
        ArrayList<Orderitems> arrayList=new ArrayList<>();
        ContentValues cv=new ContentValues();
        Cursor cursor=db.rawQuery("select order_id,Item,price,variant from ordereditems",null);
        int count;
        String oid,item,variant; int price;
        while(cursor.moveToNext()){
            count=countdistinciItems(cursor.getString(1),cursor.getInt(2));
            oid=cursor.getString(0);
            item=cursor.getString(1);
            price=cursor.getInt(2);
            variant=cursor.getString(3);
            cv.put(order_id,oid);
            cv.put(itemname,item);
            cv.put(itemprice,price);
            cv.put(itemvariant,variant);
            cv.put(iteminventory,count);
            db1.insert(orderitemdistincttable,null,cv);
        }
    }

    public int countdistinciItems(String item,int price){
        SQLiteDatabase db=this.getReadableDatabase();
        int s;
        Cursor cursor=db.rawQuery("select count(Item) from ordereditems where item='"+item+"' and price='"+price+"'",null);
        if(cursor.moveToFirst()){
            s=cursor.getInt(0);
        }
        else{
            s=0;
        }
        return s;
    }
    /*public void delete(String track){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete(carttable,"track = ?",new String[] {track});
    }*/
}
