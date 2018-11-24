package com.example.d33p.onlinefood;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomList extends BaseAdapter{

    private String[] id;
    private String[] item;
    private String[] variant;
    private String[] inventory;
    private String[] price;
    private String[] priceC;
    public String iid;
    public String iitem;
    public String ivariant;
    public String iinventory;
    public String iprice;
    SqliteDB mydb;

    List<Retro> arraylist = new ArrayList<>();
    Customviewholder customviewholder;
    private Context mContext;
    SharedPreferences sp;
    StringBuilder sb=new StringBuilder();

    Calendar c=Calendar.getInstance();
    SimpleDateFormat df=new SimpleDateFormat("HH");
        //txt.setText(df.format(c.getTime()));

    public void setItem(String[] item) {
        this.item = item;
    }

    public CustomList(Context context) {
        mContext = context;
        //sp=mContext.getSharedPreferences("com.example.d33p.onlinefood",Context.MODE_PRIVATE);
        mydb=new SqliteDB(mContext);
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(arraylist.get(position).getId());
    }

    public CustomList(String[] price) {
        this.price = price;
    }

    public CustomList(List<Retro> arraylist) {
        this.arraylist = arraylist;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos=position;
        sp = mContext.getSharedPreferences("food_items",Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit=sp.edit();
        String time=df.format(c.getTime());
        final int timeint=Integer.parseInt(time);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_items, null, false);
            customviewholder = new Customviewholder();
            customviewholder.itemname =  convertView.findViewById(R.id.itemname);
            customviewholder.price = convertView.findViewById(R.id.price);
            customviewholder.btn=convertView.findViewById(R.id.add);
            customviewholder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sb.append(((Retro)getItem(position)).getItem());
                    sb.append(",");

                    edit.putString("id",sb.toString()).apply();
                    //sp.edit().putString("id",((Retro)getItem(position)).getItem()).apply();
                    //Toast.makeText(mContext,getItem(position).toString(),Toast.LENGTH_LONG).show();
                    String s=sp.getString("id","");
                    Toast.makeText(mContext.getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    iid=((Retro) getItem(position)).getId();
                    iitem=((Retro) getItem(position)).getItem();
                    if(timeint>6 && timeint<10)
                    {
                        iprice=((Retro) getItem(position)).getPrice();
                    }
                    else{
                        iprice=((Retro) getItem(position)).getPriceC();
                    }
                    //iinventory=((Retro) getItem(position)).getInventory();
                    ivariant=((Retro) getItem(position)).getVariant();
                    mydb.insert(iid,iitem,ivariant,iprice);
                }
            });
            convertView.setTag(customviewholder);
        } else {
            customviewholder = (Customviewholder) convertView.getTag();
        }
        customviewholder.itemname.setText(arraylist.get(position).getItem());
        if(timeint>6 && timeint<=22){
            customviewholder.price.setText("Rs. "+arraylist.get(position).getPrice());
        }
        else {
            customviewholder.price.setText("Rs. " + arraylist.get(position).getPrice());
        }
        return convertView;
    }

    public class Customviewholder {
        TextView itemname,price;
        Button btn;
    }
}
