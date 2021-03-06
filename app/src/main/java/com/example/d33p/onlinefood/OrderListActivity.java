package com.example.d33p.onlinefood;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.d33p.onlinefood.DB.SqliteDB;
import com.example.d33p.onlinefood.order.Orderitems;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    TextView order,delivery;
    ListView lv;
    android.support.v7.widget.Toolbar toolbar;
    public ItemsinOrderAdapter adapter;
    ArrayList<Orderitems> arrayList;
    SqliteDB mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        String orderid=getIntent().getStringExtra("orderid");
        String deliver=getIntent().getStringExtra("deliver");
        toolbar=findViewById(R.id.toolbar);
        mydb=new SqliteDB(this);
        //order=findViewById(R.id.byorderid);
        //delivery=findViewById(R.id.delivery);
        //order.setText("Order id: "+orderid);
        //delivery.setText(deliver);
        toolbar.setTitle("Order: "+orderid);
        toolbar.setSubtitle(deliver);

        /////////old stuff//////////////////
        arrayList=new ArrayList<>();
        arrayList=mydb.getItemsinOrder(orderid);
        lv=findViewById(R.id.listview);
        adapter=new ItemsinOrderAdapter(this,arrayList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /////////new Stuff//////////////////
        //arrayList=new ArrayList<>();
        //arrayList=mydb.checkdistinctitems(orderid);
    }
}
