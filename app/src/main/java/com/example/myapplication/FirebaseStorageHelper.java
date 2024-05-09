package com.example.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseStorageHelper {
    private final StorageReference storageRef;

    public static String imageDownloadUri;


    public FirebaseStorageHelper() {

        storageRef = FirebaseStorage.getInstance().getReference();
    }



    public void uploadImage(Activity activity, Uri imageUri, final ImageView imageView) {
        if (imageUri != null) {
            // Create a reference to the location where you want to store the image in Firebase Storage
            StorageReference imageRef = storageRef.child("images/" + System.currentTimeMillis() + ".jpg");

            // Upload the file to Firebase Storage
            imageRef.putFile(imageUri)
                    .addOnSuccessListener(activity, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Image uploaded successfully, get the download URL
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri downloadUri) {
                                    // Set the download URL to the ImageView (if needed)
                                    String imageUrl = downloadUri.toString();
                                    imageDownloadUri = imageUrl;

                                    for (int justvary=1;justvary<2;justvary++){
                                        Toast.makeText(activity.getApplicationContext(), "Waiting for upload " , Toast.LENGTH_LONG).show();
                                    }
                                    //Toast.makeText(activity.getApplicationContext(), "Image URL: " + imageUrl, Toast.LENGTH_LONG).show();

                                    Toast.makeText(activity.getApplicationContext(), "Image Uploaded Successfully" , Toast.LENGTH_LONG).show();
                                    //getproduct.checking();


                                   // Toast.makeText(activity.getApplicationContext(), "Image URL: " + imageUrl, Toast.LENGTH_LONG).show();*/
                                    if (imageView != null) {
                                        imageView.setImageURI(downloadUri);
                                    }
                                }
                            });
                        }
                    });
        }
    }

    public String geturl(){

        return imageDownloadUri;
    }


}
