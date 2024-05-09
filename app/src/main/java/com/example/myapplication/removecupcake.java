package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class removecupcake extends Activity implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore firestore55;

    private FirebaseFirestore firestore;
    private TextView textView;
    private Button button;

    private Button button2;

    private Spinner spinnerremovecup;

    public static String selectedcuptoremove="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.removecupcake);

        //textView=findViewById(R.id.cupcaketoremove);
        button=(Button)findViewById(R.id.cupcaketoremovebtn);
        button2=(Button)findViewById(R.id.cupcaketoupdatebtn);
        firestore55 = FirebaseFirestore.getInstance();

        spinnerremovecup = (Spinner) findViewById(R.id.spinnerremovecup);
        spinnerremovecup.setAdapter(manage_cupcake.dropdownadapterremovecup);
        spinnerremovecup.setOnItemSelectedListener(removecupcake.this);
        Log.d("TAG", "before click: "+selectedcuptoremove);

       button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(selectedcuptoremove.equals("")){
                    Toast.makeText(removecupcake.this, "Please Select the Cupcake name", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "before click: "+selectedcuptoremove);
                }
                else{
                    RemoveProductToFirestore();
                    Toast.makeText(removecupcake.this, "successfully deleted cupcake", Toast.LENGTH_SHORT).show();
                    change_view();

                }


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                retrieveProductByName(selectedcuptoremove);
                change_view_toupdate();
                finish();

            }
        });


    }

    private void RemoveProductToFirestore() {

        CollectionReference categoryCollection = firestore55.collection("products");

        String fieldNameToDeleteBy = "name"; // Replace with your field name
        String fieldValueToDelete = selectedcuptoremove; // Replace with your field value
        Log.d("TAG", "RemoveProductToFirestore: "+selectedcuptoremove);

        Query query = categoryCollection.whereEqualTo(fieldNameToDeleteBy, fieldValueToDelete);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Loop through the matching documents and delete them
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        document.getReference().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> deleteTask) {
                                if (deleteTask.isSuccessful()) {
                                    finish();
                                    // Document deleted successfully
                                    // You can add any additional handling or feedback here
                                } else {
                                    Toast.makeText(removecupcake.this, "Please Enter the Category correctly", Toast.LENGTH_SHORT).show();
                                    // Handle the error
                                    // You can add error handling code here
                                }
                            }
                        });
                    }
                } else {
                    // Handle the query error
                    // You can add error handling code here
                }
            }
        });
}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedcuptoremove=spinnerremovecup.getSelectedItem().toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedcuptoremove="";
    }

    public void change_view(){
        Intent intent=new Intent(this,adminpanel.class);
        startActivity(intent);
    }

    public void change_view_toupdate(){
        Intent intent=new Intent(this,updateproduct.class);
        startActivity(intent);
    }

    private void retrieveProductByName(String productName) {
        firestore55.collection("products")
                .whereEqualTo("name", productName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Get the data from the document
                                String name = document.getString("name");
                                String category = document.getString("Category");
                                String price = document.getString("price");
                                String offers = document.getString("Offers");
                                String url = document.getString("Url");

                                // Now you can use the retrieved data as needed
                                // For example, display it in TextViews or perform other actions

                                // Example: Display the retrieved data in TextViews
                                updateproduct.productNameEditText.setText(name);
                                //spinner.setSelection(getIndex(spinner, category));
                                updateproduct.productPriceEditText.setText(price);
                                updateproduct.Offers.setText(offers);
                                // Set the URL wherever needed (e.g., an ImageView)

                                // You may want to add more error handling here
                            }
                        } else {
                            // Handle the query error
                            // You can add error handling code here
                        }
                    }
                });
    }


}
