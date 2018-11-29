package com.example.d33p.onlinefood.items;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.d33p.onlinefood.Api.ForApi;
import com.example.d33p.onlinefood.DB.SqliteDB;
import com.example.d33p.onlinefood.R;
import com.example.d33p.onlinefood.Api.Retro;
import com.example.d33p.onlinefood.cart.MyCart;
import com.example.d33p.onlinefood.order.Placed_orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemsList extends AppCompatActivity {

    SqliteDB mydb;
    public ListView listview;
    public CustomList listAdapter;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list);

        mydb=new SqliteDB(this);

        ImageButton imgbtn=findViewById(R.id.cart);
        ImageButton imgorder=findViewById(R.id.order);

        listview=findViewById(R.id.listview);
        listAdapter = new CustomList(this);
        listview.setAdapter(listAdapter);

        Retrofit retro=new Retrofit.Builder().baseUrl(ForApi.URL).addConverterFactory(GsonConverterFactory.create()).build();
        ForApi api=retro.create(ForApi.class);
        Call<List<Retro>> call =api.getItems();//Callback<List<Retro>>() {
        call.enqueue(new Callback<List<Retro>>() {
            @Override
            public void onResponse(Call<List<Retro>> call, Response<List<Retro>> response) {
                List<Retro> foods=response.body();
                String[] fooditem=new String[foods.size()];
                String[] foodprice=new String[foods.size()];
                for(int i=0;i<foods.size();i++){
                    fooditem[i]=foods.get(i).getItem();
                    //mydb.insertinventory(foods.get(i).getId(),foods.get(i).getItem(),Integer.parseInt(foods.get(i).getInventory()));
                }
                for(int i=0;i<foods.size();i++){
                    foodprice[i]=foods.get(i).getPrice();
                }
                /*if(a){
                    for(int i=0;i<foods.size();i++){
                        mydb.insertinventory(foods.get(i).getId(),foods.get(i).getItem(),Integer.parseInt(foods.get(i).getInventory()));
                    }
                }*/

                listAdapter.arraylist = foods;
                listview.setAdapter(listAdapter);

                a=Integer.parseInt(mydb.checkinventory());
                if(a==0){
                    for(int i=0;i<foods.size();i++){
                        mydb.insertinventory(foods.get(i).getId(),foods.get(i).getItem(),Integer.parseInt(foods.get(i).getInventory()));
                    }
                }
                System.out.println(a);
            }

            @Override
            public void onFailure(Call<List<Retro>> call, Throwable t) {
                Toast.makeText(ItemsList.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ItemsList.this,MyCart.class);
                startActivity(i);
            }
        });
        imgorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ItemsList.this,Placed_orders.class);
                startActivity(i);
            }
        });
    }
}
