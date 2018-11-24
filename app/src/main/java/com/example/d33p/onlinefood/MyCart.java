package com.example.d33p.onlinefood;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCart extends AppCompatActivity {

    SqliteDB mydb;

    ListView listview;
    Retrofit retro;
    String p,w,s ="com.example.d33p.onlinefood";
    String [] itemid,itemname,itemnames;
    CartItemsList listAdapter;
    SharedPreferences sp1,sp;
    Call<List<Retro>> call;
    ArrayList<Cartitems> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        mydb=new SqliteDB(this);
        arrayList=new ArrayList<>();
        listview=findViewById(R.id.cartlists);
        arrayList=mydb.getdata();
        listAdapter=new CartItemsList(this,arrayList);
        listview.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        //listAdapter = new CartItemsList(getApplicationContext());
        //listview.setAdapter(listAdapter);

        //retro=new Retrofit.Builder().baseUrl(GetApiId.URL).addConverterFactory(GsonConverterFactory.create()).build();

        ///////////////////////////////////////////
        /*sp1=getSharedPreferences("food_items",Context.MODE_PRIVATE);
        p=sp1.getString("id","");
        itemnames=p.split(",");
        List<String> items=new ArrayList<String>();
        for(int i=0;i<itemnames.length;i++){
            items.add(itemnames[i]);
        }
        ArrayAdapter<String> adap=new ArrayAdapter<String>(MyCart.this,R.layout.activity_my_cart,items);
        listview.setAdapter(adap);*/
        ///////////////////////////////////////////

    }
}




    /*sp=getSharedPreferences("food_items",Context.MODE_PRIVATE);
        p=sp.getString("id","");
        itemnames=new String[10];
        itemnames=p.split(",");
        GetApiId api=retro.create(GetApiId.class);
        List<String> items=new ArrayList<>();
        for(int i=0;i<itemnames.length;i++){
            //items.add(itemnames[i]);
            call=api.getItemsId(itemnames[i]);
            call.enqueue(new Callback<List<Retro>>() {
                @Override
                public void onResponse(Call<List<Retro>> call, Response<List<Retro>> response) {
                    List<Retro> foodbyid=response.body();
                    listAdapter.arraylist.clear();
                    itemid=new String[foodbyid.size()];
                    itemname=new String[foodbyid.size()];

                    listAdapter.arraylist=foodbyid;
                    listview.setAdapter(listAdapter);
                }

                @Override
                public void onFailure(Call<List<Retro>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }*/


    /*public void callid()
    {
        w=sp.getString("id","");
        itemnames=w.split(",");
        GetApiId apiid=retro.create(GetApiId.class);
        for(String i : itemnames){
            call =apiid.getItemsId(i);
            call.enqueue(new Callback<List<Retro>>() {
                @Override
                public void onResponse(Call<List<Retro>> call, Response<List<Retro>> response) {
                    List<Retro> foodbyid=response.body();
                    listAdapter.arraylist.clear();
                    itemid=new String[foodbyid.size()];
                    itemname=new String[foodbyid.size()];
                    if(response.isSuccessful() && response.body()!=null){
                        for(int i=0;i<foodbyid.size();i++){
                            itemid[i]=foodbyid.get(i).getItem();
                            itemname[i]=foodbyid.get(i).getItem();
                        }
                    }

                    listAdapter.arraylist=foodbyid;
                    listview.setAdapter(listAdapter);
                }

                @Override
                public void onFailure(Call<List<Retro>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    /*public void called()
    {
        call.enqueue(new Callback<List<Retro>>() {
            @Override
            public void onResponse(Call<List<Retro>> call, Response<List<Retro>> response) {
                List<Retro> foodbyid=response.body();
                listAdapter.arraylist.clear();
                itemid=new String[foodbyid.size()];
                itemname=new String[foodbyid.size()];
                if(response.isSuccessful() && response.body()!=null){
                    for(int i=0;i<foodbyid.size();i++){
                        itemid[i]=foodbyid.get(i).getItem();
                        itemname[i]=foodbyid.get(i).getItem();
                    }
                }

                listAdapter.arraylist=foodbyid;
                listview.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(Call<List<Retro>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }*/