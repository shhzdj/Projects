package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b07projectapplication.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreOwnerHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_homepage);
        getSupportActionBar().hide();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userUID = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(userUID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if ( task.isSuccessful() ) {
                    StoreOwner owner = task.getResult().getValue(StoreOwner.class);
                    TextView tv = findViewById(R.id.textName);
                    tv.setText( owner.getFirstName() );
                }
            }
        });
    }

    public void openProductPage(View view){
        Intent intent = new Intent(this, StoreOwner_ViewProducts.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void openMyProfile(View view){
        Intent intent = new Intent(this, StoreOwner_MyProfile.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void openOrders(View view) {
        Intent intent = new Intent(this, StoreOwner_ViewOrders.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(StoreOwnerHomepage.this, LoginActivity.class);
        startActivity(back);
        finish();
    }

}