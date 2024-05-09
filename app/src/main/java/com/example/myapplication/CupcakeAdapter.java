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
import android.widget.Toast;


import java.util.ArrayList;

public class CupcakeAdapter extends RecyclerView.Adapter<CupcakeAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<CupcakeModel> cupcakeModelArrayList;

    StringBuilder selectedCartDetails = new StringBuilder();

    static ArrayList<String> upname = new ArrayList<>();
    static ArrayList<String> upurl = new ArrayList<>();
    static ArrayList<String> upprice = new ArrayList<>();
    //String imageUrl = "https://firebasestorage.googleapis.com/v0/b/testing-99ef8.appspot.com/o/images%2F1694782293029.jpg?alt=media&token=a706cc9a-762b-4b7b-ab10-603595751975";

    // Constructor
    public CupcakeAdapter(Context context, ArrayList<CupcakeModel> cupcakeModelArrayList) {
        this.context = context;
        this.cupcakeModelArrayList = cupcakeModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        CupcakeModel model = cupcakeModelArrayList.get(position);
        holder.courseNameTV.setText(model.getCourse_name());
        holder.offer.setText(model.get_offer());
        holder.price.setText(model.get_price());
        //holder.course_rat.setText("" + model.getCourse_rating());
        //holder.courseIV.setImageResource(model.getCourse_image());
        Picasso.get().load(model.getCourse_rating()).into(holder.courseIV);

        holder.cart.setOnClickListener(v -> {
            // Build a string with the selected course details



            selectedCartDetails.setLength(0);
            selectedCartDetails.append(model.getCourse_name()).append("\n");
            upname.add(selectedCartDetails.toString());
            selectedCartDetails.setLength(0);
            //selectedCartDetails.append("Offer: ").append(model.get_offer()).append("\n");
            selectedCartDetails.append(model.get_price()).append("\n");
            upprice.add(selectedCartDetails.toString());
            selectedCartDetails.setLength(0);
            selectedCartDetails.append(model.getCourse_rating()).append("\n");
            upurl.add(selectedCartDetails.toString());
            selectedCartDetails.setLength(0);
            Toast.makeText(context, "product is added to cart ", Toast.LENGTH_SHORT).show();




            // Display the details in a Toast

            //Toast.makeText(context, ""+position+"", Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, upurl.get(0), Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, upprice.get(position), Toast.LENGTH_SHORT).show();



        });

    }

    public static String getnamecart(int index){
        return upname.get(index);
    }

    public static String geturlcart(int index1){
        return upurl.get(index1);
    }

    public static String getprice(int index2){
        return upprice.get(index2);
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return cupcakeModelArrayList.size();
    }



    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView courseIV;
        private final TextView courseNameTV;

        private  final TextView offer;

        private final TextView price;

        private Button cart;
        //private final TextView courseRatingTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseIV = itemView.findViewById(R.id.idIVCourseImage);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            offer=itemView.findViewById(R.id.offer);
            price=itemView.findViewById(R.id.price);
            cart = itemView.findViewById(R.id.cartbutton);
            //courseRatingTV = itemView.findViewById(R.id.idTVCourseRating);
        }
    }
}

