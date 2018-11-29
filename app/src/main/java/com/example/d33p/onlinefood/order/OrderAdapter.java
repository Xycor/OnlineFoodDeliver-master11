package com.example.d33p.onlinefood.order;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.d33p.onlinefood.OrderListActivity;
import com.example.d33p.onlinefood.R;
import com.example.d33p.onlinefood.DB.SqliteDB;
import com.example.d33p.onlinefood.cart.Cartitems;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {

    ArrayList<Orderitems> arraylist;
    Context mContext;
    SqliteDB mydb;
    TextView price,itemname,variant,time,tracking,deliver,order,totalitems;
    public OrderAdapter(Context context, ArrayList<Orderitems> arrayList) {
        this.arraylist = arrayList;
        this.mContext=context;
        mydb=new SqliteDB(context);
    }

    @Override
    public int getCount() {
        return this.arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return this.arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(arraylist.get(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_list_items, null);
            convertView.setBackground(mContext.getDrawable(R.drawable.order_item_style));
            //itemname=convertView.findViewById(R.id.itemname);
            //price=convertView.findViewById(R.id.price);
            //variant=convertView.findViewById(R.id.variant);
            time=convertView.findViewById(R.id.timeoforder);
            //tracking=convertView.findViewById(R.id.tracking);
            deliver=convertView.findViewById(R.id.delivery);
            order=convertView.findViewById(R.id.orderid);
            totalitems=convertView.findViewById(R.id.totalitems);
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(mContext,OrderListActivity.class);
                    i.putExtra("orderid",arraylist.get(position).getId());
                    i.putExtra("deliver",arraylist.get(position).getDeliver());
                    mContext.startActivity(i);
                }
            });
        }
        else{
            convertView.getTag();
        }
        //itemname.setText(arraylist.get(position).getItem());
        //price.setText("Rs."+arraylist.get(position).getPrice());
        //variant.setText(arraylist.get(position).getVariant());
        //tracking.setText("Tracking No. "+arraylist.get(position).getTrack());
        time.setText(arraylist.get(position).getOrdertime());
        deliver.setText(arraylist.get(position).getDeliver());
        totalitems.setText(arraylist.get(position).getTotalitems()+" Items");
        order.setText("Order: "+arraylist.get(position).getId());
        return convertView;
    }
}
