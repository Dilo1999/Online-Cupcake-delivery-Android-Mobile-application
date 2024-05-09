package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<OrderModel> orderList;

    public OrderAdapter(List<OrderModel> orderList) {
        this.orderList = orderList;
    }



    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        OrderModel orderModel = orderList.get(position);
        holder.text1TextView.setText(orderModel.getText1());
        holder.text2TextView.setText(orderModel.getText2());
        holder.text3TextView.setText(orderModel.getText3());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView text1TextView;
        public TextView text2TextView;
        public TextView text3TextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            text1TextView = itemView.findViewById(R.id.text1TextView);
            text2TextView = itemView.findViewById(R.id.text2TextView);
            text3TextView = itemView.findViewById(R.id.text3TextView);
        }
    }
}
