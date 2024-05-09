package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class manage_category extends Activity {

    private Button addcategory;

    private Button removecategory;

    public static ArrayAdapter<String> dropdownadapterremovecat;

    FirebaseFirestore firestoreremovecat = FirebaseFirestore.getInstance();

    // Define the collection reference
    CollectionReference orderDetailsCollectionremovecat = firestoreremovecat.collection("Category");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_category);
        retrieveOrderDetailsData();
        dropdownadapterremovecat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

        addcategory=(Button) findViewById(R.id.add_category_btn);
        removecategory=(Button) findViewById(R.id.remove_Cat_btn);

        addcategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addcat();

            }
        });

        removecategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    removecat();

            }
        });
    }

    public void addcat(){
        Intent intent=new Intent(this,addcategoryclass.class);
        startActivity(intent);
    }
    public void removecat(){
        Intent intent=new Intent(this,removecategoryclass.class);
        startActivity(intent);
    }

    public void retrieveOrderDetailsData() {
        // Retrieve all documents in the collection
        orderDetailsCollectionremovecat.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Access data from the document
                        //String orderId = document.getId(); // Get the document ID
                        //String  = document.getString("Address");
                        String details = document.getString("Category");
                        dropdownadapterremovecat.add(details);

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

