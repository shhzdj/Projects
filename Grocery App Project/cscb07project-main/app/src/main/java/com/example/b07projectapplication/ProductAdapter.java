package com.example.b07projectapplication;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context context;
    ArrayList<Product> list;
    ProductAdapter.buttonClickListener mylistener;

    //interface for when a store is clicked by the user
    public interface buttonClickListener{
        void onClick(int position);
    }

    public void setRecyclerViewClickListener(buttonClickListener listener){
        mylistener = listener;

    }

    public ProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product, parent, false);
        return new MyViewHolder(v, mylistener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product p = list.get(position);
        holder.productName.setText(p.getName());
        holder.price.setText(p.getPriceString());
        holder.quantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0){
                    p.setQuantity(Integer.parseInt(s.toString()));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        holder.b.setText("Add to Cart");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView productName;
        TextView price;
        EditText quantity;
        Button b;

        public MyViewHolder(@NonNull View itemView, buttonClickListener listener) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            quantity = (EditText) itemView.findViewById(R.id.product_quantity_getter);
            b = itemView.findViewById(R.id.cart_button);
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
