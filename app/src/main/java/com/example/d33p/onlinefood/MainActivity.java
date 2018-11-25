package com.example.d33p.onlinefood;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText user,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.user);
        pwd=findViewById(R.id.password);
    }
    public void various(View view){
        String userS=user.getText().toString();
        String pwdS=pwd.getText().toString();
        if(userS.equals("deep") && pwdS.equals("1234")){
            Intent i=new Intent(MainActivity.this,ItemsList.class);
            startActivity(i);
            MainActivity.this.finish();
        }
        else
        {
            Snackbar.make(view,"Invalid UserName or Password",Snackbar.LENGTH_LONG).setActionTextColor(1).show();
            user.setText("");
            pwd.setText("");
        }
        //mydb=new SqliteDB(this);
    }
}
//https://lenskart.com/black-blue-full-rim-wayfarer-small-size-49-vincent-chase-vagabond-vc-2009-c-c23-eyeglasses-130598.html