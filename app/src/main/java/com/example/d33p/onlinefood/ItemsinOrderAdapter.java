package com.example.d33p.onlinefood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.d33p.onlinefood.order.Orderitems;

import java.util.ArrayList;

public class ItemsinOrderAdapter extends BaseAdapter {

    Context mContext;
    //String item,price,variant;
    String orderid;
    TextView track,item,price,variant;
    ArrayList<Orderitems> arrayList;

    public ItemsinOrderAdapter(Context context, ArrayList<Orderitems> arrayList) {
        this.mContext = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(arrayList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.order_list_item_contents,null);
            track=convertView.findViewById(R.id.track);
            price=convertView.findViewById(R.id.price);
            variant=convertView.findViewById(R.id.variant);
            item=convertView.findViewById(R.id.item);
        }
        else{
            convertView.getTag();
        }
        item.setText(arrayList.get(position).getItem());
        price.setText(arrayList.get(position).getPrice());
        return convertView;
    }
}
