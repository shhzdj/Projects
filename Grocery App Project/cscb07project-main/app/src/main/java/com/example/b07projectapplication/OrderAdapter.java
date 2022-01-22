package com.example.b07projectapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    Context context;
    ArrayList<Order> list;
    OrderAdapter.ButtonClickListener mylistener;


    //interface for when a store is clicked by the user
    public interface ButtonClickListener{
        void onClick(int position);
    }

    public void setButtonClickListener(OrderAdapter.ButtonClickListener listener){
        mylistener = listener;

    }

    public OrderAdapter(Context context, ArrayList<Order> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order, parent, false);
        return new OrderAdapter.MyViewHolder(v, mylistener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        Order o = list.get(position);
        holder.customerName.setText("Order by: "+o.getCustomerName());
        setListView(holder.products, o.getProducts());
        holder.b.setText("Complete");

    }

    public void setListView(ListView lv, ArrayList<Product> p) {
        ArrayList<String> s = new ArrayList<String>();
        for (Product i: p) {
            s.add(i.getName() + " (" + i.getQuantity() + ")");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (context, android.R.layout.simple_list_item_1, s);
        lv.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 150 * s.size()));
        lv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView customerName;
        ListView products;
        Button b;

        public MyViewHolder(@NonNull View itemView, OrderAdapter.ButtonClickListener listener) {
            super(itemView);
            customerName = itemView.findViewById(R.id.customerName);
            products = itemView.findViewById(R.id.orderedProducts);
            b = itemView.findViewById(R.id.orderComplete);
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
