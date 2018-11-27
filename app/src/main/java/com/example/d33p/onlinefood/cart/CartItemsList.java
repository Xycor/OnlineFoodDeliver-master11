package com.example.d33p.onlinefood.cart;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.d33p.onlinefood.items.CustomList;
import com.example.d33p.onlinefood.R;
import com.example.d33p.onlinefood.DB.SqliteDB;

import java.util.ArrayList;

public class CartItemsList extends BaseAdapter {
    Button btn;
    TextView name,price, variant;
    TextView track;
    View v;
    CheckBox cod;
    //List<Retro> arraylist1 = new ArrayList<>();
    ArrayList<Cartitems> arraylist;
    private SqliteDB mydb;
    String track1;
    CustomList cl;
    private Context context;
    String name1,price1,variant1,track11;
    Boolean checkCod;
    private String d;

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
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.cart_list_items,null);
            //cl=new CustomList(context);
            btn=convertView.findViewById(R.id.place);
            v=convertView;
            //track1=cl.getTrack1();
            cod=v.findViewById(R.id.cod);
            name=v.findViewById(R.id.itemname);
            price=v.findViewById(R.id.price);
            variant=v.findViewById(R.id.variant);
            track=v.findViewById(R.id.trackk);

            /*btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //btn.setText("Order Placed: "+arraylist.get(position).getItem());
                    //btn.setEnabled(false);
                    checkCod=cod.isChecked();
                    name1=((Cartitems)getItem(position)).getItem();
                    price1=((Cartitems)getItem(position)).getPrice();
                    variant1=((Cartitems)getItem(position)).getVariant();
                    track11=((Cartitems)getItem(position)).getTrack();
                    System.out.println("///////////////////////////");
                    System.out.println(track11);
                    System.out.println("///////////////////////////");
                    if(checkCod)
                    {
                        d="Deliver by tomorrow";
                        Snackbar.make(v.getRootView(),"Order placed and delivered by tomorrow "+arraylist.get(position).getItem(),Snackbar.LENGTH_LONG).show();
                        mydb.insertorder(name1,variant1,price1,track11,d);
                        deleteitem(track1);
                    }
                    else{
                        d="Delivery will be notified";
                        mydb.insertorder(name1,variant1,price1,track11,d);
                        Snackbar.make(v.getRootView(),"Order's been placed "+arraylist.get(position).getItem(),Snackbar.LENGTH_LONG).show();
                        deleteitem(track1);
                    }
                }
            });*/

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
    /*public void deleteitem(String t){
        mydb.delete(t);
    }*/
}
