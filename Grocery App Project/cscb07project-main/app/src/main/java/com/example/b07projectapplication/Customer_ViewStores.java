package com.example.b07projectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Customer_ViewStores extends AppCompatActivity {

    static ArrayList<Store> addedStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        addedStores = new ArrayList<Store>();
        getStores();
    }

    public void viewStore(ArrayList<Store> stores) {

        if (stores == null || stores.size() == 0) {
            Log.i("check", "No stores found");
            return;
        }

        int x = (int) (Math.ceil(stores.size() / 3.0));
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView title = new TextView(this);
        title.setText(R.string.stores);
        title.setTextSize(20);
        title.setAllCaps(true);
        title.setTextColor(Color.BLACK);
        layout.addView(title);

        for (int i = 0; i < x; i++) {
            LinearLayout l = new LinearLayout(this);
            l.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            345));
            Button b1 = new Button(this);
            b1.setLayoutParams(new LinearLayout.LayoutParams(5, 5));
            b1.setVisibility(View.INVISIBLE);
            l.addView(b1);

            for (int j = 0; j < 3 && (j + (i * 3)) < stores.size(); j++) {
                Button store = new Button(this);
                store.setLayoutParams(new LinearLayout.LayoutParams(345,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                store.setText(stores.get(j + (i * 3)).storeName);
                store.setId(j + (i * 3));
                store.setBackgroundColor(Color.parseColor("#ff0099cc"));
                l.addView(store);
                Button b2 = new Button(this);
                b2.setLayoutParams(new LinearLayout.LayoutParams(5, 5));
                b2.setVisibility(View.INVISIBLE);
                l.addView(b2);
            }

            layout.addView(l);
            LinearLayout l2 = new LinearLayout(this);
            l2.setLayoutParams(new LinearLayout.LayoutParams(5, 5));
            layout.addView(l2);
        }

        addContentView(layout, new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

    }

    public void getStores() {

        //Reading from a realtime database using a persistent listener
        DatabaseReference ref =
                FirebaseDatabase.getInstance().getReference("users");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("check", "data changed");
                ArrayList<Store> stores = new ArrayList<Store>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    StoreOwner p = child.getValue(StoreOwner.class);
                    if (p.isOwner) {
                        Store a = new Store(p);
                        if(!addedStores.contains(a)) {
                            stores.add(a);
                            addedStores.add(a);
                        }
                    }
                }
                Log.i("check", "Total Stores found = " + stores.size());
                viewStore(stores);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled",
                        databaseError.toException());
            }
        };
        ref.addValueEventListener(listener);
        return;
    }

}