package com.example.d33p.onlinefood.items;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.d33p.onlinefood.Api.ForApi;
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

    public ListView listview;
    public CustomList listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list);

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
                }
                for(int i=0;i<foods.size();i++){
                    foodprice[i]=foods.get(i).getPrice();
                }

                listAdapter.arraylist = foods;
                listview.setAdapter(listAdapter);
//                listAdapter.notifyDataSetChanged();




                //listview.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_list_items,fooditem));
                /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        /*Intent i=new Intent(MainActivity.this,Main2Activity.class);
                        i.putExtra("FoodItem",listview.getItemAtPosition(position).toString());
                        startActivity(i);
                    }
                });*/
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
