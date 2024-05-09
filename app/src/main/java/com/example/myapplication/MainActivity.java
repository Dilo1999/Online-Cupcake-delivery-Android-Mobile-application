package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView textView22;

    private  EditText Signinemail;

    private  EditText signinpassword;
    private FirestoreUtils firestoreUtils;
    private static final String TAG2 = "FirestoreUtils";
    private TextView adminLoginLink;

    public static String useremailafterlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button) findViewById(R.id.Admin_log_Btn);
        textView22=findViewById(R.id.textView11);
        signinpassword=findViewById(R.id.Password_new);
        Signinemail=findViewById(R.id.Email_new);
        firestoreUtils = new FirestoreUtils();

        // Initialize the TextView
        adminLoginLink = findViewById(R.id.Admin_log_link);

        // Set an OnClickListener for the TextView
        adminLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Admin_login activity when the TextView is clicked
                Intent intent = new Intent(MainActivity.this, Admin_login.class);
                startActivity(intent);
            }
        });






        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String emailToCheck = Signinemail.getText().toString().trim();
                String passwordToCheck = signinpassword.getText().toString().trim();

                Toast.makeText(MainActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();
                Log.d(TAG2, "clicked");
                firestoreUtils.checkUserExists(emailToCheck, passwordToCheck, new FirestoreUtils.UserExistenceCallback() {
                    @Override
                    public void onUserExists(boolean exists, String role) {
                        if (exists) {
                            if ("user".equals(role)) {
                                openpageproductdisplay();
                                useremailafterlogin=emailToCheck;
                                Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                //wrong
                                // Launch User Activity
                                //Log.d(TAG2, "outside and user");

                            } else if ("Admin".equals(role)) {
                               //openpage2();
                                // Launch Admin Activity
                                //Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                                Log.d(TAG2, "outside and user:"+role);
                                Toast.makeText(MainActivity.this, "Wrong Email Or Password", Toast.LENGTH_SHORT).show();
                                useremailafterlogin=emailToCheck;

                            } else {
                                Log.d(TAG2, "outside and user user:"+role);

                               // Toast.makeText(MainActivity.this, "Welcome User: "+emailToCheck, Toast.LENGTH_SHORT).show();
                                useremailafterlogin=emailToCheck;
                                // Launch a default activity or show a message
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong Email Or Password ", Toast.LENGTH_SHORT).show();
                            // Email and password combination does not exist
                            // Handle this case here
                        }
                    }
                });


               // Toast.makeText(MainActivity.this, emailToCheck+"   pswd:"+passwordToCheck, Toast.LENGTH_SHORT).show();
            }
        });


        textView22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openpagereg();
            }
        });
    }

    public void openpage2(){
        Intent intent=new Intent(this,adminpanel.class);
        startActivity(intent);
    }

    public void openpageproductdisplay(){
        Intent intent=new Intent(this,ProductDisplay.class);
        //Intent intent=new Intent(this,GetOrderActivity.class);
        startActivity(intent);
    }

    public void openpagereg(){
        Intent intent=new Intent(this,registration.class);
        startActivity(intent);
    }
}