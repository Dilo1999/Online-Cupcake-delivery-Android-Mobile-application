package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<CartModel> cartModelArrayList;

    public static final HashMap<String, Integer> selectedCartDetailsMap = new HashMap<>();


    StringBuilder selectedCartDetails = new StringBuilder();

    //String imageUrl = "https://firebasestorage.googleapis.com/v0/b/testing-99ef8.appspot.com/o/images%2F1694782293029.jpg?alt=media&token=a706cc9a-762b-4b7b-ab10-603595751975";

    // Constructor
    public CartAdapter(Context context, ArrayList<CartModel> cartModelArrayList) {
        this.context = context;
        this.cartModelArrayList = cartModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        CartModel model = cartModelArrayList.get(position);
        holder.courseNameTV.setText(model.getCourse_name());
        holder.price.setText(model.get_price());

        String itemName = "\n"+model.getCourse_name()+"  "+model.get_price();

        // Check if the item name is already in the map, if not, add it with a default quantity of 1
        if (!selectedCartDetailsMap.containsKey(itemName)) {
            selectedCartDetailsMap.put(itemName, 1);
        }
        //holder.courseRatingTV.setText("" + model.getCourse_rating());
        //holder.courseIV.setImageResource(model.getCourse_image());
        Picasso.get().load(model.getCourse_rating()).into(holder.courseIV);

        holder.quantity.setText(String.valueOf(model.getQuantity()));

        holder.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "increase btn", Toast.LENGTH_SHORT).show();
                int currentQuantity = model.getQuantity();
                String itemName = "\n"+model.getCourse_name()+"  "+model.get_price();
                // Increment the quantity
                model.setQuantity(currentQuantity + 1);
                holder.quantity.setText(String.valueOf(model.getQuantity()));
                String priceinseidecart = model.get_price();
                String price2 = priceinseidecart.replaceAll("[^0-9]", "");
                if (!price2.isEmpty()) {
                    cart_main.totalpricecheck = cart_main.totalpricecheck + Integer.parseInt(price2);
                    cart_main.printtotoal.setText(String.valueOf(cart_main.totalpricecheck));
                }
                selectedCartDetailsMap.put(itemName, currentQuantity + 1);
                //cart_main.totalpricecheck=cart_main.totalpricecheck+model.get_price();
                //Toast.makeText(context, "increase btn"+model.get_price(), Toast.LENGTH_SHORT).show();

            }
        });

        holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "decrease btn", Toast.LENGTH_SHORT).show();
                int currentQuantity = model.getQuantity();
                // Ensure the quantity doesn't go below 0
                if (currentQuantity > 1) {
                    // Decrement the quantity
                    model.setQuantity(currentQuantity - 1);
                    holder.quantity.setText(String.valueOf(model.getQuantity()));
                    String itemName = "\n"+model.getCourse_name()+"  "+model.get_price();
                    String priceinseidecart = model.get_price();
                    String price2 = priceinseidecart.replaceAll("[^0-9]", "");
                    if (!price2.isEmpty()) {
                        cart_main.totalpricecheck = cart_main.totalpricecheck - Integer.parseInt(price2);
                        cart_main.printtotoal.setText(String.valueOf(cart_main.totalpricecheck));
                    }
                    selectedCartDetailsMap.put(itemName, currentQuantity - 1);


                    // Update the selected cart details if needed
                    // You can adjust the details in the selectedCartDetails StringBuilder here
                }

            }
        });

    }


    public String getselectedcart(){
        return selectedCartDetails.toString();
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return cartModelArrayList.size();
    }



    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView courseIV;
        private final TextView courseNameTV;

        //private  final TextView offer;

        private final TextView price;

        private Button cart;

        private final Button increaseButton; // Button for increasing quantity
        private final Button decreaseButton;

        private final TextView quantity;
        //private final TextView courseRatingTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseIV = itemView.findViewById(R.id.idIVCourseImage);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            //offer=itemView.findViewById(R.id.offer);
            price=itemView.findViewById(R.id.price);

            increaseButton=itemView.findViewById(R.id.buttonincqty);
            decreaseButton=itemView.findViewById(R.id.Admin_log_Btn2);
            quantity = itemView.findViewById(R.id.quantity);
            //cart = itemView.findViewById(R.id.cartbutton);
            //courseRatingTV = itemView.findViewById(R.id.idTVCourseRating);
        }
    }
}

