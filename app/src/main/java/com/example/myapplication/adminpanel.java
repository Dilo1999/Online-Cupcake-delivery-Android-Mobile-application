package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminpanel extends Activity {

    private Button managecupake;

    private Button managecategory;

    private Button vieworder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);

        managecupake=(Button) findViewById(R.id.manage_caupake_btn);
        managecategory=(Button) findViewById(R.id.manage_category_btn);
        vieworder=(Button) findViewById(R.id.vieworder_btn);

        managecupake.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cupcake();

            }
        });

        managecategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                categorygo();

            }
        });

        vieworder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                govieworder();

            }
        });




    }
    public void cupcake(){
        Intent intent=new Intent(this,manage_cupcake.class);
        startActivity(intent);
    }
    public void categorygo(){
        Intent intent=new Intent(this,manage_category.class);
        startActivity(intent);
    }
    public void govieworder(){
        Intent intent=new Intent(this,GetOrderActivity.class);
        startActivity(intent);
    }
}
