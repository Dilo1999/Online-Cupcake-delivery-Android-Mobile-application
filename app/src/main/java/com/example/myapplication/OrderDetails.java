package com.example.myapplication;

import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class OrderDetails {

    private String address;
    private String details;

    public OrderDetails() {
        // Default constructor required for Firestore
    }

    public OrderDetails(String address, String details) {
        this.address = address;
        this.details = details;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("address", address);
        map.put("details", details);
        return map;
    }

    public static void main(String[] args) {
        // Example of creating a Firestore document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        OrderDetails order = new OrderDetails("123 Main St", "Order details here");

        // Add a new document with a generated ID
        db.collection("orderdetails")
                .add(order.toMap())
                .addOnSuccessListener(documentReference -> {
                    System.out.println("Document added successfully with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    System.err.println("Error adding document: " + e);
                });
    }
}

