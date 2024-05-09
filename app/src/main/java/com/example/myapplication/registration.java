package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



public class registration extends AppCompatActivity{

    private FirebaseFirestore firestore;

    private EditText Email;

    private EditText Password;

    private EditText confirmPassword;

    private EditText Firstname;

    private EditText Lastname;
    private Button reguserbtn;

    private FirebaseFirestore firestore234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        Email=findViewById(R.id.Regemail);
        Password=findViewById(R.id.Regpassword);
        Lastname=findViewById(R.id.lastname);
        Firstname=findViewById(R.id.Firstname);
        confirmPassword=findViewById(R.id.confirmpassword);


        reguserbtn=findViewById(R.id.Reguser);

        firestore234 = FirebaseFirestore.getInstance();


        reguserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email.getText().toString().trim().equals("")||Password.getText().toString().trim().equals("")||Firstname.getText().toString().trim().equals("")||Lastname.getText().toString().trim().equals("")){
                    Toast.makeText(registration.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();


                }
                else if(confirmPassword.getText().toString().equals(Password.getText().toString().trim())){
                    saveUserToFirestore();
                }
                else{
                    Toast.makeText(registration.this, "Password Doesn't match", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(registration.this, "Successfull", Toast.LENGTH_SHORT).show();


                }
                }
        });






    }

    private void saveUserToFirestore() {
        String userEmail = Email.getText().toString().trim();
        String userPassword = Password.getText().toString().trim();
        String userFirstn=Firstname.getText().toString().trim();
        String userlastn=Lastname.getText().toString().trim();

        // Check if the user with this email already exists
        firestore234.collection("UserDetails")
                .document(userEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // A user with this email already exists
                                // You can handle this case (e.g., show an error message)
                                Toast.makeText(getApplicationContext(), "User Already exists Please try again  ", Toast.LENGTH_SHORT).show();
                            } else {
                                // Create a new user document
                                Map<String, Object> user = new HashMap<>();
                                user.put("Email", userEmail);
                                user.put("Password", userPassword);
                                user.put("Role", "user");
                                user.put("FirstName",userFirstn);
                                user.put("LastName",userlastn);

                                // Add the user to Firestore with the email as the document ID
                                firestore234.collection("UserDetails")
                                        .document(userEmail)
                                        .set(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(registration.this, "Successfull", Toast.LENGTH_SHORT).show();
                                                    // User saved successfully
                                                    finish(); // Close the activity
                                                } else {
                                                    // Handle the error
                                                    // You can add error handling code here
                                                }
                                            }
                                        });
                            }
                        } else {
                            // Handle the error
                            // You can add error handling code here
                        }
                    }
                });
    }



}
