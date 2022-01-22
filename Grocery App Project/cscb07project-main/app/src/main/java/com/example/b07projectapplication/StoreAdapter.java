package com.example.b07projectapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
    Context context;
    ArrayList<StoreOwner> list;
    RecyclerViewClickListener mylistener;


    //interface for when a store is clicked by the user
    public interface RecyclerViewClickListener{
        void onClick(int position);
    }

    public void setRecyclerViewClickListener(RecyclerViewClickListener listener){
        mylistener = listener;
    }


    public StoreAdapter(Context context, ArrayList<StoreOwner> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.store, parent, false);
        return new MyViewHolder(v, mylistener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StoreOwner p = list.get(position);
        holder.storeName.setText(p.getStoreName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView storeName;

        public MyViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            storeName = itemView.findViewById(R.id.store_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        listener.onClick(pos);
                    }
                }
            });
        }
    }

}
