package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addcategoryclass extends Activity {

    private FirebaseFirestore firestore55;

    private FirebaseFirestore firestore;

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcategory);

        textView = findViewById(R.id.categorytoadd);
        button = (Button) findViewById(R.id.categorytoaddbtn);
        firestore55 = FirebaseFirestore.getInstance();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(textView.getText().toString().isEmpty()){
                    Toast.makeText(addcategoryclass.this, "Please Enter the value for Category", Toast.LENGTH_SHORT).show();
                }
                else{
                    saveProductToFirestore();
                    Toast.makeText(addcategoryclass.this, "successfully added the new category", Toast.LENGTH_SHORT).show();
                    change_view_to_refresh();
                }


            }
        });
    }
    private void saveProductToFirestore() {
        String Categoryaddfirestore =textView.getText().toString().trim();


        // Create a new product map
        Map<String, Object> product = new HashMap<>();
        product.put("Category", Categoryaddfirestore);


        // Add the product to Firestore
        firestore55.collection("Category")
                .add(product)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Product saved successfully
                            finish(); // Close the activity
                        } else {
                            Toast.makeText(addcategoryclass.this, "Please Enter the Category correctly", Toast.LENGTH_SHORT).show();
                            // Handle the error
                            // You can add error handling code here
                        }
                    }
                });
    }

    public void change_view_to_refresh(){
        Intent intent=new Intent(this,adminpanel.class);
        startActivity(intent);
    }
}
