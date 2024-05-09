package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class updateproduct extends Activity implements AdapterView.OnItemSelectedListener {
    static EditText productNameEditText;
    private EditText product_Category;
    static EditText productPriceEditText;
    private Button saveButton;
    static EditText Offers;

    static ImageView imageView;

    private Uri imageUri;

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button chooseimagebtn;

    private Button uploadImage;

    private FirebaseFirestore firestore55;
    private Spinner spinner;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_product);

        spinner = (Spinner) findViewById(R.id.updatespinner);

        // Initialize Firebase Firestore
        firestore55 = FirebaseFirestore.getInstance();

        //show image before upload
        imageView = findViewById(R.id.updateimageViewProduct);

        // Initialize UI elements
        productNameEditText = findViewById(R.id.updateTextProductName);
        //product_Category = findViewById(R.id.Category);
        productPriceEditText = findViewById(R.id.updateTextProductPrice);
        saveButton = findViewById(R.id.updatebuttonSave);
        chooseimagebtn=findViewById(R.id.updatechooseImageButton);
        Offers=findViewById(R.id.updateOffers);
        uploadImage=findViewById(R.id.updateuploadImage);

        spinner.setAdapter(manage_cupcake.dropdownadapter);

        spinner.setOnItemSelectedListener(updateproduct.this);


        final  FirebaseStorageHelper storageHelperupdate = new FirebaseStorageHelper();

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageHelperupdate.uploadImage(updateproduct.this, imageUri, imageView);


            }
        });

        chooseimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getproduct.this, storageHelper.geturl(), Toast.LENGTH_SHORT).show();
                String after=storageHelperupdate.geturl();

                if(productNameEditText.getText().toString().equals("")&&productPriceEditText.getText().toString().equals("")&&Offers.getText().toString().equals("")){
                    Toast.makeText(updateproduct.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();

                }else{
                    //saveProductToFirestore(after);
                    //change_view();
                    UpdateProductInFirestore(after);

                }

                //
            }
        });

    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void UpdateProductInFirestore(String yt) {
        CollectionReference categoryCollection = firestore55.collection("products");

        String fieldNameToUpdateBy = "name"; // Replace with your field name
        String fieldValueToUpdate = removecupcake.selectedcuptoremove; // Replace with your field value

        String newCategoryValue = spinner.getSelectedItem().toString(); // Replace with the new category value
        String newPriceValue = productPriceEditText.getText().toString().trim(); // Replace with the new price value
        String newOffersValue = Offers.getText().toString().trim(); // Replace with the new offers value
        String newUrlValue = yt; // Replace with the new URL value
        String newnametoupdate=productNameEditText.getText().toString().trim();;

        Query query = categoryCollection.whereEqualTo(fieldNameToUpdateBy, fieldValueToUpdate);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Loop through the matching documents and update them
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Update multiple fields in the document
                        document.getReference().update("Category", newCategoryValue,
                                        "price", newPriceValue,
                                        "Offers", newOffersValue,
                                        "Url", newUrlValue,"name",newnametoupdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> updateTask) {
                                        if (updateTask.isSuccessful()) {
                                            finish();
                                            // Document updated successfully
                                            // You can add any additional handling or feedback here
                                        } else {
                                            Toast.makeText(updateproduct.this, "Failed to update document", Toast.LENGTH_SHORT).show();
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
