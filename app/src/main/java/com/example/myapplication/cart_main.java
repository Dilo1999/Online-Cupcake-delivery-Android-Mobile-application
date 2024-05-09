package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cart_main extends AppCompatActivity {

    public static int totalpricecheck=0;
    private Button clearcart;

    private Button checkout;

    private static String orderdetails="";

    private static String email=MainActivity.useremailafterlogin;

    public static TextView printtotoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cart_all);


        clearcart=(Button)findViewById(R.id.clearcart);
        checkout=(Button)findViewById(R.id.Checkout);
        printtotoal=findViewById(R.id.totalPrice);

        // Here, we have created new array list and added data to it
         ArrayList<CartModel> cartModelArrayList = new ArrayList<CartModel>();

        int size= CupcakeAdapter.upname.size();
        calculate(size,cartModelArrayList);
        //Toast.makeText(this,""+size+"", Toast.LENGTH_SHORT).show();
        //calculate(size,cartModelArrayList);

        clearcart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cartModelArrayList.clear();
                CupcakeAdapter.upname.clear();
                CupcakeAdapter.upurl.clear();
                CupcakeAdapter.upprice.clear();
                orderdetails="";
                totalpricecheck=0;
                printtotoal.setText("Rs 0.00");
                int size= CupcakeAdapter.upname.size();
                //Toast.makeText(cart_main.this,""+size+"", Toast.LENGTH_SHORT).show();
                //Toast.makeText(cart_main.this,"clicked", Toast.LENGTH_SHORT).show();
                calculate(size,cartModelArrayList);

            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openpagecheckout();

            }
        });

    }
    private void calculate(int size,ArrayList<CartModel> cartModelArrayList){
        for (int i = 0; i <= size; i++) {
            if(i==0){
                //Toast.makeText(this, "Nothing to see", Toast.LENGTH_SHORT).show();
            }
            else{
                String name= CupcakeAdapter.getnamecart(i-1);
                String url= CupcakeAdapter.geturlcart(i-1);
                String price= CupcakeAdapter.getprice(i-1);
                String price2 = price.replaceAll("[^0-9]", "");
                if (!price2.isEmpty()) {
                    totalpricecheck = totalpricecheck + Integer.parseInt(price2);
                }

                //Toast.makeText(this, ""+name+"", Toast.LENGTH_SHORT).show();
                cartModelArrayList.add(new CartModel(name,url,1,"50",price));
                orderdetails=orderdetails+name+price;



            }
            //CourseAdapter.upname.clear();
        }
        if(!cartModelArrayList.isEmpty()){
            //Toast.makeText(this, "NOt empty cart", Toast.LENGTH_SHORT).show();
            printtotoal.setText("Rs "+String.valueOf(totalpricecheck));
        }

        //Toast.makeText(this, ""+totalpricecheck+"", Toast.LENGTH_SHORT).show();
        RecyclerView courseRV = findViewById(R.id.idRVCourse);
        CartAdapter cartAdapter = new CartAdapter(this, cartModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //Toast.makeText(this, ""+orderdetails+"", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "To user:"+email, Toast.LENGTH_SHORT).show();

        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(cartAdapter);
    }

    public void openpage2(){
        Intent intent=new Intent(this,getproduct.class);
        startActivity(intent);
    }

    public static String getaftercheckout(){
        return orderdetails;
    }
    public static String getaftercheckoutemail(){
        return email;
    }

    public void openpagecheckout(){
        Intent intent=new Intent(this,checkout.class);
        startActivity(intent);
    }

    }
