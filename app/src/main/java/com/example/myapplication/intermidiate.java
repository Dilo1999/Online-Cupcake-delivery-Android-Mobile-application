package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class intermidiate extends Activity {

    private Button gocart;
    private Button goshopping;

    private Button Yourorderpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermidiate);

        gocart=(Button)findViewById(R.id.cart);
        goshopping=(Button)findViewById(R.id.order);
        Yourorderpage=(Button)findViewById(R.id.gotoorder);

        goshopping.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openpage2();

            }
        });

        gocart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openpage3();

            }
        });

        Yourorderpage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openpage4();

            }
        });



    }

    public void openpage2(){
        Intent intent=new Intent(this,ProductDisplay.class);
        startActivity(intent);
    }

    public void openpage3(){
        Intent intent=new Intent(this,cart_main.class);
        startActivity(intent);
    }

    public void openpage4(){
        Intent intent=new Intent(this,yourorder.class);
        startActivity(intent);
    }


}
