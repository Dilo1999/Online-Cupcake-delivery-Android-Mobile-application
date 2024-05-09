package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

public class checkout extends Activity {

    private TextView orderdetails_checkoutview;

    private Button placeorder;

    private TextView address;

    private FirebaseFirestore firestore55;

    private FirebaseFirestore firestore;

    public static String Usercheckouts;

   public static String afterusercheckoutemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_view);
        placeorder=(Button)findViewById(R.id.Place_order);
        orderdetails_checkoutview=(TextView) findViewById(R.id.checkoutdetails);
        address=findViewById(R.id.Address);
        orderdetails_checkoutview.setText(cart_main.getaftercheckout());
        firestore55 = FirebaseFirestore.getInstance();
        Log.d("TAG", "onCreate: "+CartAdapter.selectedCartDetailsMap.toString());

        placeorder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String userInput = address.getText().toString().trim(); // Trim removes leading and trailing spaces

                // Check if the user input is empty
                if (userInput.isEmpty()) {
                    // Show an error message or handle the case where input is empty
                    Toast.makeText(getApplicationContext(), "Please enter Valid Address", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG",address.getText().toString().trim());
                    Usercheckouts=cart_main.getaftercheckout();
                    afterusercheckoutemail=cart_main.getaftercheckoutemail();
                    saveProductToFirestore();
                    Toast.makeText(getApplicationContext(), "Order Placed Successfuly. Thank you", Toast.LENGTH_SHORT).show();
                }




            }
        });







        };

    private void saveProductToFirestore() {
        String User_address = address.getText().toString().trim();
        String Order_Details =CartAdapter.selectedCartDetailsMap.toString();
        String Order_email=cart_main.getaftercheckoutemail();
        String wholeprice=cart_main.printtotoal.getText().toString();
        Log.d("TAG", "saveProductToFirestore: "+wholeprice);

        /*String price = productPriceEditText.getText().toString().trim();
        String P_offers=Offers.getText().toString().trim();*/


        // Create a new product map
        Map<String, Object> product = new HashMap<>();
        product.put("Address",User_address);
        product.put("Details", Order_Details);
        product.put("Email",Order_email);
        product.put("Full Price",wholeprice);


        //product.put("Role","user");
        /*
        product.put("price", price);
        product.put("Offers",P_offers);
        product.put("Url",yt);

         */


        // Add the product to Firestore
        firestore55.collection("OrderDetails")
                .add(product)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Product saved successfully
                            finish(); // Close the activity
                        } else {
                            // Handle the error
                            // You can add error handling code here
                        }
                    }
                });
    }
}


