package com.example.d33p.onlinefood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.d33p.onlinefood.DB.SqliteDB;
import com.example.d33p.onlinefood.order.Orderitems;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    TextView order;
    ListView lv;
    public ItemsinOrderAdapter adapter;
    ArrayList<Orderitems> arrayList;
    SqliteDB mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        String orderid=getIntent().getStringExtra("orderid");
        mydb=new SqliteDB(this);
        order=findViewById(R.id.byorderid);
        order.setText(orderid);
        arrayList=new ArrayList<>();
        arrayList=mydb.getItemsinOrder(orderid);
        lv=findViewById(R.id.listview);
        adapter=new ItemsinOrderAdapter(this,arrayList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
