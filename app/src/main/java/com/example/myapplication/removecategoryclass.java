package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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




public class removecategoryclass extends Activity implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore firestore55;

    //private FirebaseFirestore firestore;
    //private TextView textView;
    private Button button;
    private Button updatebutton;

    private Spinner spinnerremovecat;

    private String selectedcattoremove;

    private EditText display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.removecategory);

        //textView=findViewById(R.id.categorytoremove);
        button = (Button) findViewById(R.id.categorytoremovebtn);
        updatebutton = (Button) findViewById(R.id.categorytoupdatebtn);

        firestore55 = FirebaseFirestore.getInstance();

        spinnerremovecat = (Spinner) findViewById(R.id.spinnerremovecat);

        spinnerremovecat.setAdapter(manage_category.dropdownadapterremovecat);
        display = findViewById(R.id.updatecat);


        spinnerremovecat.setOnItemSelectedListener(removecategoryclass.this);

        //FirebaseFirestore db = FirebaseFirestore.getInstance();
        //String collectionName = "Category";


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (selectedcattoremove.isEmpty() || spinnerremovecat.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(removecategoryclass.this, "Please Select the Category", Toast.LENGTH_SHORT).show();
                } else {
                    RemoveProductToFirestore();
                    //Toast.makeText(removecategoryclass.this, "successfully deleted category", Toast.LENGTH_SHORT).show();
                    Toast.makeText(removecategoryclass.this, "successfully deleted category", Toast.LENGTH_SHORT).show();
                    change_view();
                }


            }
        });

        updatebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UpdateCategoryInFirestore();
                UpdateProductInFirestore();
                gotoadmin();


            }
        });

    }

    private void RemoveProductToFirestore() {

        CollectionReference categoryCollection = firestore55.collection("Category");

        String fieldNameToDeleteBy = "Category"; // Replace with your field name
        String fieldValueToDelete = selectedcattoremove;// Replace with your field value

        Query query = categoryCollection.whereEqualTo(fieldNameToDeleteBy, fieldValueToDelete);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            final boolean[] found = {false};

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    // Loop through the matching documents and delete them
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        document.getReference().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> deleteTask) {
                                if (deleteTask.isSuccessful()) {
                                    found[0] = true;
                                    Toast.makeText(removecategoryclass.this, "successfully deleted category", Toast.LENGTH_SHORT).show();
                                    finish();
                                    // Document deleted successfully
                                    // You can add any additional handling or feedback here
                                } else {

                                    //Toast.makeText(removecategoryclass.this, "Please Enter the Category correctly", Toast.LENGTH_SHORT).show();
                                    // Handle the error
                                    // You can add error handling code here
                                }
                            }
                        });
                    }

                } else {

                    if (!found[0]) {
                        Toast.makeText(removecategoryclass.this, "Please Enter a Valid Category Name", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(removecategoryclass.this, "Please Enter the Category correctly", Toast.LENGTH_SHORT).show();
                    // Handle the query error
                    // You can add error handling code here
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        selectedcattoremove = spinnerremovecat.getSelectedItem().toString().trim();
        display.setText(selectedcattoremove);
        Log.d("TAG", "onItemSelected: " + selectedcattoremove);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedcattoremove = "";
    }

    public void change_view() {
        Intent intent = new Intent(this, adminpanel.class);
        startActivity(intent);
    }

    private void UpdateCategoryInFirestore() {
        CollectionReference categoryCollection = firestore55.collection("Category");

        String fieldNameToUpdateBy = "Category"; // Replace with your field name
        String fieldValueToUpdate = selectedcattoremove; // Replace with your field value
        String newCategoryValue = display.getText().toString(); // Replace with the new category value

        Query query = categoryCollection.whereEqualTo(fieldNameToUpdateBy, fieldValueToUpdate);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            final boolean[] found = {false};

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Loop through the matching documents and update them
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Update the "Category" field with the new value
                        document.getReference().update(fieldNameToUpdateBy, newCategoryValue)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> updateTask) {
                                        if (updateTask.isSuccessful()) {
                                            found[0] = true;
                                            Toast.makeText(removecategoryclass.this, "Category updated successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                            // Document updated successfully
                                            // You can add any additional handling or feedback here
                                        } else {
                                            // Handle the error
                                            // You can add error handling code here
                                        }
                                    }
                                });
                    }
                } else {
                    if (!found[0]) {
                        Toast.makeText(removecategoryclass.this, "Please Enter a Valid Category Name", Toast.LENGTH_SHORT).show();
                    }
                    // Handle the query error
                    // You can add error handling code here
                }
            }
        });
    }

    public void gotoadmin() {
        Intent intent = new Intent(this, adminpanel.class);
        startActivity(intent);
    }

    private void UpdateProductInFirestore() {
        CollectionReference categoryCollection = firestore55.collection("products");

        String fieldNameToUpdateBy = "Category"; // Replace with your field name
        String fieldValueToUpdate = selectedcattoremove; // Replace with your field value

        String newCategoryValue = display.getText().toString(); // Replace with the new category value
        //String newPriceValue = productPriceEditText.getText().toString().trim(); // Replace with the new price value
        //String newOffersValue = Offers.getText().toString().trim(); // Replace with the new offers value
        //String newUrlValue = yt; // Replace with the new URL value
        //String newnametoupdate=productNameEditText.getText().toString().trim();;

        Query query = categoryCollection.whereEqualTo(fieldNameToUpdateBy, fieldValueToUpdate);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Loop through the matching documents and update them
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Update multiple fields in the document
                        document.getReference().update("Category", newCategoryValue)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> updateTask) {
                                        if (updateTask.isSuccessful()) {
                                            finish();
                                            // Document updated successfully
                                            // You can add any additional handling or feedback here
                                        } else {
                                            Toast.makeText(removecategoryclass.this, "Failed to update document", Toast.LENGTH_SHORT).show();
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
}




