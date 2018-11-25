package com.example.d33p.onlinefood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Placed_orders extends AppCompatActivity {

    public ListView listview;
    public OrderList listAdapter;
    SqliteDB mydb;
    ArrayList<Cartitems> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_orders);
        mydb=new SqliteDB(this);
        arrayList=new ArrayList<>();
        listview=findViewById(R.id.orderlists);
        arrayList=mydb.getdataOrder();
        listAdapter=new OrderList(this,arrayList);
        listview.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }
}
