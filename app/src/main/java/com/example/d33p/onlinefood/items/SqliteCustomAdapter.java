package com.example.d33p.onlinefood.items;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d33p.onlinefood.Api.Retro;
import com.example.d33p.onlinefood.DB.SqliteDB;
import com.example.d33p.onlinefood.R;
import com.example.d33p.onlinefood.cart.Cartitems;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SqliteCustomAdapter extends BaseAdapter {

    private String[] id;
    private String[] item;
    private String[] variant;
    //private String[] inventory;
    private String[] price;
    private String[] priceC;
    public String iid;
    public String iitem;
    public String ivariant;
    public int iinventory;
    public int iprice;
    public String itrack;
    public String track;
    public int timeint,i;
    public SqliteDB mydb;

    ArrayList<FoodItemsList> arraylist = new ArrayList<>();
    SqliteCustomAdapter.Customviewholder customviewholder;
    private Context mContext;
    SharedPreferences sp;
    StringBuilder sb=new StringBuilder();
    SimpleDateFormat df;
    private String track1;
    TextView quantity;

    public String getTrack1() {
        return track1;
    }
    //txt.setText(df.format(c.getTime()));

    public void setItem(String[] item) {
        this.item = item;
    }

    public SqliteCustomAdapter(Context context,ArrayList<FoodItemsList> arraylist) {
        this.mContext = context;
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
        return Long.parseLong(this.arraylist.get(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //final int pos=position;
        //sp = mContext.getSharedPreferences("food_items",Context.MODE_PRIVATE);
        //final SharedPreferences.Editor edit=sp.edit();
        //System.out.println("                "+track);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_items, null, false);
            customviewholder = new SqliteCustomAdapter.Customviewholder();
            customviewholder.itemname =  convertView.findViewById(R.id.itemname);
            customviewholder.price = convertView.findViewById(R.id.price);
            customviewholder.variant = convertView.findViewById(R.id.variant);
            customviewholder.btn=convertView.findViewById(R.id.add);
            customviewholder.inventory=convertView.findViewById(R.id.inventory);
            quantity=convertView.findViewById(R.id.quantity);

            df=new SimpleDateFormat("HH");
            customviewholder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar c=Calendar.getInstance();
                    SimpleDateFormat forTrack=new SimpleDateFormat("yyMMddHHmmss");
                    track=forTrack.format(c.getTime());
                    String time=df.format(c.getTime());
                    timeint=Integer.parseInt(time);
                    itrack=track1=track;
                    //iid=((Retro) getItem(position)).getId();
                    iitem=arraylist.get(position).getItem();
                    if(timeint>6 && timeint<=22)
                    {
                        iprice=arraylist.get(position).getPrice();
                    }
                    else{
                        iprice=arraylist.get(position).getPricec();
                    }
                    ivariant=arraylist.get(position).getVariant();
                    iinventory=arraylist.get(position).getInventory();
                    i=Integer.parseInt(quantity.getText().toString());
                    mydb.operateinventory(arraylist.get(position).getId(),i);
                    mydb.insertcart(iitem,ivariant,iprice,itrack);
                    Toast.makeText(mContext,"Added to Cart "+iitem,Toast.LENGTH_LONG).show();

                }
            });
            convertView.setTag(customviewholder);
        } else {
            customviewholder = (SqliteCustomAdapter.Customviewholder) convertView.getTag();
        }
        customviewholder.itemname.setText(arraylist.get(position).getItem());
        customviewholder.variant.setText(arraylist.get(position).getVariant());
        if(timeint>6 && timeint<=22){
            customviewholder.price.setText("Rs. "+arraylist.get(position).getPrice());
        }
        else {
            customviewholder.price.setText("Rs. " + arraylist.get(position).getPricec());
        }
        customviewholder.inventory.setText(arraylist.get(position).getInventory()+" items in stock");
        return convertView;
    }

    public class Customviewholder {
        TextView itemname,price,variant,inventory;
        Button btn;
    }
}
