package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class yourorder extends Activity {

    private OrderAdapter orderAdapter123;
    private List<OrderModel> orderItems123 = new ArrayList<>();
    private List<OrderModel> orderList123= new ArrayList<>() ;

    private ArrayList<String> checkoutdetailsarray = new ArrayList<>();


     RecyclerView recyclerView;
      // Updated adapter name


    private TextView textView;

    private Button btn;

    //ArrayList<String> yourorderonly = new ArrayList<>();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    // Define the collection reference
    CollectionReference orderDetailsCollection = firestore.collection("OrderDetails");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usercheckouts);

        recyclerView = findViewById(R.id.recyclerView234);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //textView=(TextView)findViewById(R.id.Yourorders);
        //textView.setMovementMethod(new ScrollingMovementMethod());



        btn=(Button) findViewById(R.id.checkyourorder);
        retrieveOrderDetailsData();

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                orderItems123.clear();
                orderList123.clear();
                //orderItems123.add(new OrderModel("","Order No: ",""));

                Log.d("TAG", "onClick: ");
                int sixexy=ProductDisplay.yourorderonly123456.size();
                Log.d("TAG", "onClick size: "+sixexy);
                for (int li=0;li<sixexy;li++){
                    String xd=ProductDisplay.yourorderonly123456.get(li);
                    int orderno=li+1;
                    Log.d("TAG", "onClick size: "+xd);
                    orderItems123.add(new OrderModel("",xd,"Order No: "+orderno));

                }
                //textView.setText(ProductDisplay.yourorderonly123456.toString());

                orderList123 = orderItems123;
                orderAdapter123 = new OrderAdapter(orderList123); // Updated adapter name
                recyclerView.setAdapter(orderAdapter123);
                ProductDisplay.yourorderonly123456.clear();

            }

            //
        });



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
                        //ProductDisplay.yourorderonly123456.clear();
                        String emailtocheck = document.getString("Email");
                        String email = document.getString("Address");
                        String details = document.getString("Details");
                        Log.d("TAG", "onComplete: "+details+emailtocheck+MainActivity.useremailafterlogin);

                        if (emailtocheck != null && MainActivity.useremailafterlogin != null) {
                            //ProductDisplay.yourorderonly123456.clear();
                            if (emailtocheck.equals(MainActivity.useremailafterlogin)) {
                                ProductDisplay.yourorderonly123456.add(email + "\n" + details);
                            }
                        } else {
                            // Handle the case where emailtocheck or useremailafterlogin is null
                        }

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
