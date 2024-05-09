package com.example.myapplication;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FirestoreUtils {

    private static final String TAG = "FirestoreUtils";
    private FirebaseFirestore firestore;
    private CollectionReference userDetailsCollection;

    public FirestoreUtils() {
        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();
        userDetailsCollection = firestore.collection("UserDetails");
    }


    public void checkUserExists(String email, String password, final UserExistenceCallback callback) {
        userDetailsCollection
                .whereEqualTo("Email", email)
                .whereEqualTo("Password", password)
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                // Email and password combination exists
                                DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                                String role = document.getString("Role");

                                // Log role for debugging
                                Log.d(TAG, "User role: " + role);

                                // Handle the role here
                                if ("User".equals(role) || "Admin".equals(role)) {
                                    callback.onUserExists(true, role); // Pass the role to the callback
                                    Log.d(TAG, "happen ");
                                } else {
                                    // Handle other roles if needed
                                    callback.onUserExists(true, role);
                                    Log.d(TAG, "happening 2");
                                }
                            } else {
                                // Email and password combination does not exist
                                callback.onUserExists(false, null);
                                Log.d(TAG, "nothing");
                            }
                        } else {
                            Log.e(TAG, "Error checking user existence: ", task.getException());
                            // Handle error here
                            callback.onUserExists(false, null);
                            Log.d(TAG, "No one");
                        }
                    }
                });
    }


    public interface UserExistenceCallback {
        void onUserExists(boolean exists, String role); // Include the user's role in the callback
    }


}
