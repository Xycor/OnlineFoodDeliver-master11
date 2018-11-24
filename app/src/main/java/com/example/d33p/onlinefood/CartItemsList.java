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
    TextView name,price, variant;
    View v;
    CheckBox cod;
    List<Retro> arraylist1 = new ArrayList<>();
    ArrayList<Cartitems> arraylist;
    private Context context;

    public CartItemsList(Context context,ArrayList<Cartitems> arraylist) {
        this.context = context;
        this.arraylist=arraylist;
    }

    @Override
    public int getCount() {
        return this.arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(arraylist.get(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //v=convertView;
        //SharedPreferences sp=context.getSharedPreferences("food_items",Context.MODE_PRIVATE);
        //SharedPreferences
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.cart_list_items,null);

            //String w=sp.getString("id","");
            cod=convertView.findViewById(R.id.cod);
            name=convertView.findViewById(R.id.itemname);
            price=convertView.findViewById(R.id.price);
            variant=convertView.findViewById(R.id.variant);
            btn=convertView.findViewById(R.id.place);
            v=convertView;
            btn.setEnabled(false);
            if(cod.isChecked()){
                //Toast.makeText(context.getApplicationContext(),arraylist.get(position).getItem(),Toast.LENGTH_LONG).show();
                btn.setEnabled(true);
            }
            else{
                btn.setEnabled(false);
            }
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn.setText("Order Placed");
                    btn.setEnabled(false);
                    Snackbar.make(v.getRootView(),"Order's been placed",Snackbar.LENGTH_LONG).show();
                }
            });
        }
        else{
            convertView.getTag();
        }
        name.setText(arraylist.get(position).getItem());
        price.setText(arraylist.get(position).getPrice());
        variant.setText(arraylist.get(position).getVariant());
        return convertView;
    }
}
