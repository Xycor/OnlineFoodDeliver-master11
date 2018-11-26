package com.example.d33p.onlinefood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderList extends BaseAdapter {

    ArrayList<Cartitems> arraylist;
    Context mContext;
    SqliteDB mydb;
    TextView price,itemname,variant,time,tracking,deliver;
    public OrderList(Context context,ArrayList<Cartitems> arrayList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_list_items, null);
            itemname=convertView.findViewById(R.id.itemname);
            price=convertView.findViewById(R.id.price);
            variant=convertView.findViewById(R.id.variant);
            time=convertView.findViewById(R.id.timee);
            tracking=convertView.findViewById(R.id.tracking);
            deliver=convertView.findViewById(R.id.deliver);
        }
        else{
            convertView.getTag();
        }
        itemname.setText(arraylist.get(position).getItem());
        price.setText("Rs."+arraylist.get(position).getPrice());
        variant.setText(arraylist.get(position).getVariant());
        tracking.setText("Tracking No. "+arraylist.get(position).getTrack());
        deliver.setText(arraylist.get(position).getDeliver());
        return convertView;
    }
}
