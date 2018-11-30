package com.example.d33p.onlinefood.items;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.d33p.onlinefood.cart.Cartitems;
import com.example.d33p.onlinefood.cart.MyCart;
import com.example.d33p.onlinefood.order.Placed_orders;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemsList extends AppCompatActivity {


    SwipeRefreshLayout refresh;

    public SqliteDB mydb;
    public ListView listview;
    //public CustomList listAdapter;
    public SqliteCustomAdapter listAdapter1;
    ArrayList<FoodItemsList> arrayList;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list);

        refresh=findViewById(R.id.refresh);

        refresh.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,R.color.colorPrimaryDark);

        mydb=new SqliteDB(this);

        ImageButton imgbtn=findViewById(R.id.cart);
        ImageButton imgorder=findViewById(R.id.order);

        //listAdapter = new CustomList(this);
        //listview.setAdapter(listAdapter);
        //listview.setAdapter(listAdapter);

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
                }
                for(int i=0;i<foods.size();i++){
                    foodprice[i]=foods.get(i).getPrice();
                }
                //listAdapter.arraylist = foods;
                //listview.setAdapter(listAdapter);

                a=Integer.parseInt(mydb.checkinventory());
                if(a==0){
                    for(int i=0;i<foods.size();i++){
                        mydb.insertinventory(foods.get(i).getId(),foods.get(i).getItem(),foods.get(i).getVariant(),
                                Integer.parseInt(foods.get(i).getInventory()),
                                Integer.parseInt(foods.get(i).getPrice()),
                                Integer.parseInt(foods.get(i).getPriceC()));
                    }
                }
                show();
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

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                        show();
                    }
                },3000);
            }
        });

    }
    public void show(){
        listview=findViewById(R.id.listview);
        arrayList=new ArrayList<>();
        arrayList=mydb.getdatalistitems();
        listAdapter1 = new SqliteCustomAdapter(this,arrayList);
        listview.setAdapter(listAdapter1);
        listAdapter1.notifyDataSetChanged();
    }
}
