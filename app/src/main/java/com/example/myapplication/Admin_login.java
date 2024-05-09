package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_login extends AppCompatActivity {

    private Button btn;

    private EditText Signinemail;

    private  EditText signinpassword;

    private FirestoreUtils firestoreUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        btn=(Button) findViewById(R.id.Admin_log_Btn2);
        signinpassword=findViewById(R.id.Password_new_2);
        Signinemail=findViewById(R.id.Email_new2);
        Log.d("TAG2", "awaw");
        firestoreUtils = new FirestoreUtils();

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String emailToCheck = Signinemail.getText().toString().trim();
                String passwordToCheck = signinpassword.getText().toString().trim();

                Toast.makeText(Admin_login.this, "Please Wait", Toast.LENGTH_SHORT).show();
                Log.d("TAG2", "clicked");

                firestoreUtils.checkUserExists(emailToCheck, passwordToCheck, new FirestoreUtils.UserExistenceCallback() {
                    @Override
                    public void onUserExists(boolean exists, String role) {
                        if (exists) {
                            if ("user".equals(role)) {
                                //openpageproductdisplay();
                                //useremailafterlogin=emailToCheck;
                                Toast.makeText(Admin_login.this, "Wrong Email or password", Toast.LENGTH_SHORT).show();
                                //wrong
                                // Launch User Activity
                                //Log.d(TAG2, "outside and user");

                            } else if ("Admin".equals(role)) {
                                openpage2();
                                // Launch Admin Activity
                                //Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                                Log.d("TAG2", "outside and user:"+role);
                                //Toast.makeText(Admin_login.this, "Wrong Email Or Password", Toast.LENGTH_SHORT).show();
                                //useremailafterlogin=emailToCheck;

                            } else {
                                Log.d("TAG2", "outside and user user:"+role);

                                // Toast.makeText(MainActivity.this, "Welcome User: "+emailToCheck, Toast.LENGTH_SHORT).show();
                                //useremailafterlogin=emailToCheck;
                                // Launch a default activity or show a message
                            }
                        } else {
                            Toast.makeText(Admin_login.this, "Wrong Email Or Password ", Toast.LENGTH_SHORT).show();
                            // Email and password combination does not exist
                            // Handle this case here
                        }
                    }
                });


                // Toast.makeText(MainActivity.this, emailToCheck+"   pswd:"+passwordToCheck, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openpage2(){
        Intent intent=new Intent(this,adminpanel.class);
        startActivity(intent);
    }


}