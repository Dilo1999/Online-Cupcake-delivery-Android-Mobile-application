package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vieworder_admin extends Activity {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    // Define the collection reference
    CollectionReference orderDetailsCollection = firestore.collection("OrderDetails");

    ArrayList<String> ordersAdmin = new ArrayList<>();

    private TextView textView;
    private Button button;

    String ordersfull="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_order);

        textView = (TextView) findViewById(R.id.vieworder);
        textView.setMovementMethod(new ScrollingMovementMethod());
        button=(Button) findViewById(R.id.get_order_admin);
        retrieveOrderDetailsData();


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView.setText("");
                ordersfull="";
                int size=ordersAdmin.size();
                for (int l=0;l<size;l++){
                    int orderno=l+1;
                    ordersfull=ordersfull+"--------------------------------------------------------"+"\n"+"Order no:"+orderno+"\n"+ordersAdmin.get(l);
                    textView.setText(ordersfull);
                }

            }
        });




    }

    public void retrieveOrderDetailsData() {
        // Retrieve all documents in the collection
        orderDetailsCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Access data from the document
                        //String orderId = document.getId(); // Get the document ID
                        String email = document.getString("Address");
                        String details = document.getString("Details");
                        ordersAdmin.add("\n Address:"+email+"\n Details:"+details+"\n");

                        // Use the retrieved data as needed
                        //Log.d("OrderDetails", "Document ID: " + orderId);
                        //Log.d("OrderDetails", "Email: " + email);
                        Log.d("OrderDetails", "Details: " + details);

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
