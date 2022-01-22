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

public class OrderAdapterCustomer extends RecyclerView.Adapter<OrderAdapterCustomer.MyViewHolder> {
    Context context;
    ArrayList<Order> list;
    OrderAdapterCustomer.ButtonClickListener mylistener;

    public interface ButtonClickListener{
        void onClick(int position);
    }

    public void setButtonClickListener(OrderAdapterCustomer.ButtonClickListener listener){
        mylistener = listener;
    }

    public OrderAdapterCustomer(Context context, ArrayList<Order> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderAdapterCustomer.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order, parent, false);
        return new OrderAdapterCustomer.MyViewHolder(v, mylistener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapterCustomer.MyViewHolder holder, int position) {
        Order o = list.get(position);
            holder.customerName.setText("Order from: " + o.storeName);
            setListView(holder.products, o.getProducts());
            if (!o.getIsComplete()) { holder.b.setVisibility(View.INVISIBLE); }
            else{holder.b.setText("Received");}
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

        public MyViewHolder(@NonNull View itemView, OrderAdapterCustomer.ButtonClickListener listener) {
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
