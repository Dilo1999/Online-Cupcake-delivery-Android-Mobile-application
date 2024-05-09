package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Categorydatagetter {
    private Context context;

    public static List<String> P_name= new ArrayList<>();
    public static List<String> P_price=new ArrayList<>();

    public static List<String> P_category=new ArrayList<>();

    public static List<String> P_offers=new ArrayList<>();

    public static List<StringBuilder> P_url=new ArrayList<>();


    public Categorydatagetter(Context context) {

        this.context = context;
    }

    public interface DataCallback {
        void onDataRetrieved(
                String[] P_name,
                String[] P_price,
                String[] P_category,
                String[] P_offers,
                StringBuilder[] P_url,
                int itemCount
        );

        void onError(Exception e);
    }



    public void retrieveAndDisplayData(String collectionName, final DataCallback callback) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = firestore.collection(collectionName);

        collectionRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> P_name = new ArrayList<>();
                            List<String> P_price = new ArrayList<>();
                            List<String> P_category = new ArrayList<>();
                            List<String> P_offers = new ArrayList<>();
                            List<StringBuilder> P_url = new ArrayList<>();


                            for (DocumentSnapshot document : task.getResult()) {
                                // Assuming each document has the required fields
                                Log.d("TAG", "takesnapshot");
                                String name = document.getString("name");
                                P_name.add(name);
                                String prc = document.getString("price");
                                P_price.add(prc);
                                String cat = document.getString("Category");
                                P_category.add(cat);
                                String off = document.getString("Offers");
                                P_offers.add(off);
                                Log.d("TAG", "oncomplete retrieve P-offer "+name);
                                StringBuilder longString1 = new StringBuilder();
                                longString1.append(document.getString("Url"));
                                P_url.add(longString1);
                            }
                            Log.d("TAG", "oncomplete outside for ");
                            // Convert the lists to arrays
                            String[] P_nameArray = P_name.toArray(new String[0]);
                            String[] P_priceArray = P_price.toArray(new String[0]);
                            String[] P_categoryArray = P_category.toArray(new String[0]);
                            String[] P_offersArray = P_offers.toArray(new String[0]);
                            StringBuilder[] P_urlArray = P_url.toArray(new StringBuilder[0]);

                            // Calculate the total item count
                            int itemCount = task.getResult().size();
                            Log.d("TAG", "oncomplete count "+itemCount);
                            // Call the callback with the retrieved data
                            callback.onDataRetrieved(P_nameArray, P_priceArray, P_categoryArray, P_offersArray, P_urlArray, itemCount);
                        } else {
                            // Handle errors here
                            callback.onError(task.getException());
                        }
                    }
                });
    }


}
