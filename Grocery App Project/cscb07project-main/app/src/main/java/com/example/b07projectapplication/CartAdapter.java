package com.example.b07projectapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    ArrayList<Product> list;
    CartAdapter.buttonClickListener mylistener;

    public interface buttonClickListener{
        void onClick(int position);
    }

    public void setRecyclerViewClickListener(buttonClickListener listener){
        mylistener = listener;

    }

    public CartAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart, parent, false);
        return new MyViewHolder(v, mylistener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product c = list.get(position);
        holder.productName.setText(c.getName());
        holder.price.setText("$"+String.valueOf(c.getPrice()*c.getQuantity()));
        holder.quantity.setText(String.valueOf(c.getQuantity()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView productName;
        TextView price;
        TextView quantity;
        ImageButton b;

        public MyViewHolder(@NonNull View itemView, buttonClickListener listener) {
            super(itemView);
            productName = itemView.findViewById(R.id.cart_product_name);
            price = itemView.findViewById(R.id.cart_product_price);
            quantity = itemView.findViewById(R.id.cart_product_quantity);
            b = itemView.findViewById(R.id.remove_cart_button);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int pos = getAdapterPosition();
                        listener.onClick(pos);
                    }

                }
            });

        }
    }

}
