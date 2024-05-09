package com.example.myapplication;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.util.Log;

public class GetOrderActivity extends Activity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter; // Updated adapter name
    private List<OrderModel> orderList;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    // Define the collection reference
    CollectionReference orderDetailsCollection = firestore.collection("OrderDetails");

    ArrayList<String> ordersAdmin = new ArrayList<>();
    ArrayList<String> ordersAdminaddress = new ArrayList<>();

    ArrayList<String> ordersFulltotal = new ArrayList<>();


    private Button button;

    String ordersfull="";
    String ordersfulladdress="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_order);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Updated method name

        // Updated adapter name

        button=findViewById(R.id.get_order_admin_new);

        List<OrderModel> orderItems = new ArrayList<>();

        retrieveOrderDetailsData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int size=ordersAdmin.size();
                for (int l=0;l<size;l++){
                    int orderno=l+1;
                    ordersfull=ordersfull+"\n"+"Order no:"+orderno+"\n"+ordersAdmin.get(l);
                    //textView.setText(ordersfull);
                    orderItems.add(new OrderModel(ordersAdminaddress.get(l), ordersAdmin.get(l),ordersFulltotal.get(l)));
                }

                orderList = orderItems;
                orderAdapter = new OrderAdapter(orderList); // Updated adapter name
                recyclerView.setAdapter(orderAdapter);
                ordersAdmin.clear();
                ordersAdminaddress.clear();
                ordersFulltotal.clear();
            }
        });

    }

    /*
    private List<OrderModel> generateOrderItems() {
        //List<OrderModel> orderItems = new ArrayList<>();
        //orderItems.add(new OrderModel("Text 1", "Text 2", "Text 3"));
        //rderItems.add(new OrderModel("Text 1", "Text 2", "Text 3"));
        //orderItems.add(new OrderModel("Text 1", "Text 2", "Text 3"));
        // Add more items here as needed
        //return orderItems;

    }

     */

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
                        String Totalfullinside=document.getString("Full Price");
                        ordersAdmin.add("Details:"+details+"");
                        ordersAdminaddress.add("Address:"+email);
                        ordersFulltotal.add("Total:"+Totalfullinside);

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
