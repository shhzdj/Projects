package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07projectapplication.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreOwner_MyProfile extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private String userUID;
    private StoreOwner owner;
    private TextView firstName;
    private TextView lastName;
    private TextView storeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_my_profile);
        getSupportActionBar().hide();

        firstName = findViewById(R.id.textShowFirstName);
        lastName = findViewById(R.id.textShowLastName);
        storeName = findViewById(R.id.textShowStoreName);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userUID = user.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(userUID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    owner = task.getResult().getValue(StoreOwner.class);
                    firstName.setText( owner.getFirstName() );
                    lastName.setText( owner.getLastName() );
                    storeName.setText( owner.getStoreName() );
                }
            }
        });
    }

    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Successfully signed out!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StoreOwner_MyProfile.this, LoginActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(StoreOwner_MyProfile.this, StoreOwnerHomepage.class);
        startActivity(back);
        finish();
    }

}