package com.example.d33p.onlinefood;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartItemsList extends BaseAdapter {
    Button btn;
    TextView name,price, variant,track;
    View v;
    CheckBox cod;
    List<Retro> arraylist1 = new ArrayList<>();
    ArrayList<Cartitems> arraylist;
    SqliteDB mydb;
    String track1;
    CustomList cl;
    private Context context;
    String name1,price1,variant1,track11;
    Boolean checkCod;

    public CartItemsList(Context context,ArrayList<Cartitems> arraylist) {
        this.context = context;
        this.arraylist=arraylist;
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
        //v=convertView;
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.cart_list_items,null);
            cl=new CustomList(context);
            btn=convertView.findViewById(R.id.place);
            v=convertView;
            track1=cl.track1;
            //String w=sp.getString("id","");
            cod=v.findViewById(R.id.cod);
            name=v.findViewById(R.id.itemname);
            price=v.findViewById(R.id.price);
            variant=v.findViewById(R.id.variant);
            track=v.findViewById(R.id.track);
            //track11=track1;
            //btn.setEnabled(false);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //btn.setText("Order Placed: "+arraylist.get(position).getItem());
                    //btn.setEnabled(false);
                    checkCod=cod.isChecked();
                    name1=((Cartitems)getItem(position)).getItem();
                    price1=((Cartitems)getItem(position)).getPrice();
                    variant1=((Cartitems)getItem(position)).getVariant();
                    track11=((Cartitems)getItem(position)).getTrack();
                    //checkCod=
                    if(checkCod)
                    {
                        String d="Deliver by tomorrow";
                        Snackbar.make(v.getRootView(),"Order placed and delivered by tomorrow "+arraylist.get(position).getItem(),Snackbar.LENGTH_LONG).show();
                        mydb.insertcart(name1,variant1,price1,track11,d);
                        //mydb.delete(track1);
                    }
                    else{
                        String d="Delivery will be notified";
                        mydb.insertcart(name1,variant1,price1,track11,d);
                        Snackbar.make(v.getRootView(),"Order's been placed "+arraylist.get(position).getItem(),Snackbar.LENGTH_LONG).show();
                        //mydb.delete(track1);
                    }
                }
            });
        }
        else{
            convertView.getTag();
        }
        name.setText(arraylist.get(position).getItem());
        price.setText("Rs."+arraylist.get(position).getPrice());
        variant.setText(arraylist.get(position).getVariant());
        track.setText(arraylist.get(position).getTrack());
        return convertView;
    }
}
