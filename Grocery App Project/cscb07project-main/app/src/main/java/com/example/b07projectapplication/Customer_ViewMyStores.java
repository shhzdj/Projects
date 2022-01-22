package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.b07projectapplication.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Customer_ViewMyStores extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref;
    StoreAdapter adapter;
    ArrayList<StoreOwner> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_my_stores);
        getSupportActionBar().hide();


        recyclerView = findViewById(R.id.stores_view);
        ref = FirebaseDatabase.getInstance().getReference("users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new StoreAdapter(this, list);
        recyclerView.setAdapter(adapter);

        adapter.setRecyclerViewClickListener(new StoreAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(int position) {
                //pass the id of the clicked store
                String id = list.get(position).getUserUID();
                sendToProducts(id);
            }
        });

        updateList();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateList();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void updateList(){
        ref = FirebaseDatabase.getInstance().getReference("users");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    list.clear();
                    for (DataSnapshot data: task.getResult().getChildren()){
                        StoreOwner o = data.getValue(StoreOwner.class);
                        if (o.isOwner){
                            System.out.println(o.getStoreName());
                            list.add(o);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void sendToProducts(String id){
        Intent intent = new Intent(Customer_ViewMyStores.this, Customer_ViewProducts.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("userid",id);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(Customer_ViewMyStores.this, CustomerHomePage.class);
        startActivity(back);
        finish();
    }

}