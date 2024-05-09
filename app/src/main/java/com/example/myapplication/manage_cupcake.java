package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.OnCompleteListener;

import java.util.ArrayList;

public class manage_cupcake extends Activity {

    private Button addcupcake;

    private Button removecupcake;

    //to update remove cupcake spinner
    public static ArrayAdapter<String> dropdownadapterremovecup;



    // Define the collection reference


    public static ArrayList<String> category_fordropdown = new ArrayList<>();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    CollectionReference orderDetailsCollectionremovecup = firestore.collection("products");

    // Define the collection reference
    CollectionReference orderDetailsCollection = firestore.collection("Category");
    private static android.content.Context mContext;
    public static ArrayAdapter<String> dropdownadapter; //= new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managecupcake);
        retrieveOrderDetailsData();
        retrieveOrderDetailsDataforremovecup();
        dropdownadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        dropdownadapterremovecup = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

        addcupcake=(Button) findViewById(R.id.addcupcakebtn);
        removecupcake=(Button) findViewById(R.id.removecupcakebtn);

        addcupcake.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addcupcake();
                Log.d("Ordercategory", category_fordropdown.toString());



            }
        });

        removecupcake.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                removecupcake();

            }
        });
    }

    public void addcupcake(){
        Intent intent=new Intent(this,getproduct.class);
        startActivity(intent);
    }
    public void removecupcake(){
        Intent intent=new Intent(this,removecupcake.class);
        startActivity(intent);
    };

    public void retrieveOrderDetailsData() {
        // Retrieve all documents in the collection
        orderDetailsCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Access data from the document
                        //String orderId = document.getId(); // Get the document ID
                        //String  = document.getString("Address");
                        String details = document.getString("Category");
                        dropdownadapter.add(details);

                        // Use the retrieved data as needed
                        //Log.d("OrderDetails", "Document ID: " + orderId);
                        //Log.d("OrderDetails", "Email: " + email);
                        Log.d("Ordercategory", "Details: " + details);

                        // You can perform further processing or display the data here
                    }
                } else {
                    // Handle the error
                    Log.e("OrderDetails", "Error getting documents: " + task.getException());
                }
            }
        });

    }

    public void retrieveOrderDetailsDataforremovecup() {
        // Retrieve all documents in the collection
        orderDetailsCollectionremovecup.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Access data from the document
                        //String orderId = document.getId(); // Get the document ID
                        //String  = document.getString("Address");
                        String details = document.getString("name");
                        dropdownadapterremovecup.add(details);

                        // Use the retrieved data as needed
                        //Log.d("OrderDetails", "Document ID: " + orderId);
                        //Log.d("OrderDetails", "Email: " + email);
                        Log.d("Ordercategory", "Details: " + details);

                        // You can perform further processing or display the data here
                    }
                } else {
                    // Handle the error
                    Log.e("OrderDetails", "Error getting documents: " + task.getException());
                }
            }
        });
    }

}
