package com.example.d33p.onlinefood.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.d33p.onlinefood.R;
import com.example.d33p.onlinefood.DB.SqliteDB;
import com.example.d33p.onlinefood.cart.Cartitems;

import java.util.ArrayList;

public class Placed_orders extends AppCompatActivity {

    public ListView listview;
    public OrderAdapter listAdapter;
    TextView amount;
    SqliteDB mydb;
    ArrayList<Orderitems> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_orders);

        mydb=new SqliteDB(this);
        String tot=mydb.getTotalofOrders();

        //amount=findViewById(R.id.amount);
        //amount.setText("Amount Payable Rs."+tot);
        arrayList=new ArrayList<>();
        listview=findViewById(R.id.orderlists);
        arrayList=mydb.gettoorder();
        listAdapter=new OrderAdapter(this,arrayList);
        listview.setAdapter(listAdapter);
        //listAdapter.notifyDataSetChanged();
    }
}
