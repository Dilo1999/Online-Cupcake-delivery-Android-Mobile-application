package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Categorymodel> categorymodelArrayList;


    //String imageUrl = "https://firebasestorage.googleapis.com/v0/b/testing-99ef8.appspot.com/o/images%2F1694782293029.jpg?alt=media&token=a706cc9a-762b-4b7b-ab10-603595751975";

    // Constructor
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private static OnItemClickListener itemClickListener;

    public CategoryAdapter(Context context, ArrayList<Categorymodel> categorymodelArrayList) {
        this.context = context;
        this.categorymodelArrayList = categorymodelArrayList;

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        Categorymodel model = categorymodelArrayList.get(position);
        holder.courseNameTV.setText(model.getCourse_name());
        //holder.courseRatingTV.setText("" + model.getCourse_rating());
        //holder.courseIV.setImageResource(model.getCourse_image());
        //Picasso.get().load(model.getCourse_rating()).into(holder.courseIV);

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return categorymodelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final ImageView courseIV;
        private final TextView courseNameTV;
        //private final TextView courseRatingTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            final Animation scaleUpAnimation = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_up_animation);
            final Animation scaledownAnimation = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.scale_down);// Create the scaling animation


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        view.startAnimation(scaleUpAnimation);
                        itemClickListener.onItemClick(position);
                    }
                }
            });



        }

    }
}

