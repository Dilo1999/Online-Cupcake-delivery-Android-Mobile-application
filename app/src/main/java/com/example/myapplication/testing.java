package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.Serializable;

public class testing extends AppCompatActivity {
    private EditText editTextData;
    private Button buttonSave;
    private TextView textViewSavedData;
    private DatabaseReference usersReference; // Reference to the "users" node in Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);

        editTextData = findViewById(R.id.editTextData);
        buttonSave = findViewById(R.id.buttonSave);
        textViewSavedData = findViewById(R.id.textViewSavedData);

        // Initialize the database reference to the "users" node
        usersReference = FirebaseDatabase.getInstance().getReference().child("users");

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFirebase();
            }
        });
    }

    public class DataObject implements Serializable {
        private String data;

        public DataObject() {
            // Default constructor required for Firebase
        }

        public DataObject(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    private void saveDataToFirebase() {
        String data = editTextData.getText().toString().trim();

        if (!data.isEmpty()) {
            // Generate a unique key for each data entry
            String key = usersReference.push().getKey();

            // Create a data object
            DataObject dataObject = new DataObject(data);

            // Save the data to the "users" node in Firebase
            usersReference.child(key).setValue(dataObject);

            // Clear the EditText
            editTextData.setText("");

            // Display a success message
            textViewSavedData.setText("Data saved to Firebase!");
        }
    }
}
