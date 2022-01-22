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

public class Customer_ViewProfile extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference ref;
    private TextView first_name;
    private TextView last_name;
    private String id;
    private Button sign_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_profile);
        Intent intent = getIntent();
        getSupportActionBar().hide();

        first_name = findViewById(R.id.firstName);
        last_name = findViewById(R.id.lastName);
        sign_out = findViewById(R.id.btnSign_out);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        id = user.getUid();

        ref = FirebaseDatabase.getInstance().getReference("users").child(id);
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Person user = task.getResult().getValue(Person.class);
                    first_name.setText(user.getFirstName());
                    last_name.setText(user.getLastName());
                }
            }
        });

    }

    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Successfully signed out!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Customer_ViewProfile.this, LoginActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(Customer_ViewProfile.this, CustomerHomePage.class);
        startActivity(back);
        finish();
    }

}