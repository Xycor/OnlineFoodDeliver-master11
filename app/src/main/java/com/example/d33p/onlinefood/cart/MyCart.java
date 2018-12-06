package com.example.d33p.onlinefood.cart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.d33p.onlinefood.order.Placed_orders;
import com.example.d33p.onlinefood.R;
import com.example.d33p.onlinefood.Api.Retro;
import com.example.d33p.onlinefood.DB.SqliteDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;

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
    TextView total,empty;
    Button place;
    CheckBox check;
    String d,ordertrack,timeorder;
    int a;
    String sss="Order placed";
    private View view;

    private Observable<String> observable;
    private Observer<String> observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        ImageButton imgorder=findViewById(R.id.order);
        total=findViewById(R.id.total);
        place=findViewById(R.id.place);
        check=findViewById(R.id.cod);
        empty=findViewById(R.id.emptycart);
        empty.setVisibility(View.INVISIBLE);


        mydb=new SqliteDB(this);
        a=Integer.parseInt(mydb.checkcart());
        System.out.println("/////////////////////////////////////////////////////////////"+a);

        if(a==0){
            place.setVisibility(View.INVISIBLE);
            total.setVisibility(View.INVISIBLE);
            check.setVisibility(View.INVISIBLE);
            empty.setVisibility(View.VISIBLE);
        }
        else if(a>0){
            place.setVisibility(View.VISIBLE);
            total.setVisibility(View.VISIBLE);
            check.setVisibility(View.VISIBLE);
            empty.setVisibility(View.INVISIBLE);
        }

        String tot=mydb.getTotalofItems();

        total.setText("Total: Rs."+tot+" to pay");

        arrayList=new ArrayList<>();
        listview=findViewById(R.id.cartlists);
        arrayList=mydb.getdata();
        listAdapter=new CartItemsList(this,arrayList);
        listview.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();


        observable=Observable.just(sss).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                if(check.isChecked()){
                    d="Deliver by Tomorrow";
                }
                else{
                    d="Delivery will be notified";
                }
                Calendar c=Calendar.getInstance();
                SimpleDateFormat forTrack=new SimpleDateFormat("yyMMddHHmmss");
                SimpleDateFormat timeoforder=new SimpleDateFormat("EEE,MMM d,''yy, h:mm a");
                ordertrack=forTrack.format(c.getTime());
                timeorder=timeoforder.format(c.getTime());
                mydb.insertorders(ordertrack,d,timeorder);
                mydb.insdelcart();
                mydb.checkdistinctitems(ordertrack);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error "+e.getMessage());
            }

            @Override
            public void onComplete() {
                listview.setVisibility(view.INVISIBLE);
                Snackbar.make(view,"Order Placed",Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.GREEN)
                        .setAction("Proceed To My Orders", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i=new Intent(MyCart.this,Placed_orders.class);
                                startActivity(i);
                            }
                        }).show();
                place.setVisibility(view.INVISIBLE);
                total.setVisibility(view.INVISIBLE);
                check.setVisibility(view.INVISIBLE);
                empty.setVisibility(view.VISIBLE);
                System.out.println("Successfully completed");
            }
        };

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view=v;
                observable.subscribe(observer);
                /*if(check.isChecked()){
                    d="Deliver by Tomorrow";
                }
                else{
                    d="Delivery will be notified";
                }
                Calendar c=Calendar.getInstance();
                SimpleDateFormat forTrack=new SimpleDateFormat("yyMMddHHmmss");
                SimpleDateFormat timeoforder=new SimpleDateFormat("EEE,MMM d,''yy, h:mm a");
                ordertrack=forTrack.format(c.getTime());
                timeorder=timeoforder.format(c.getTime());
                mydb.insertorders(ordertrack,d,timeorder);
                mydb.insdelcart();
                mydb.checkdistinctitems(ordertrack);
                listview.setVisibility(v.INVISIBLE);
                Snackbar.make(v,"Order Placed",Snackbar.LENGTH_LONG)
                        .setActionTextColor(Color.GREEN)
                    .setAction("Proceed To My Orders", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(MyCart.this,Placed_orders.class);
                            startActivity(i);
                        }
                }).show();
                place.setVisibility(v.INVISIBLE);
                total.setVisibility(v.INVISIBLE);
                check.setVisibility(v.INVISIBLE);
                empty.setVisibility(v.VISIBLE);*/
            }
        });

        imgorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyCart.this,Placed_orders.class);
                startActivity(i);
            }
        });
    }
}



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