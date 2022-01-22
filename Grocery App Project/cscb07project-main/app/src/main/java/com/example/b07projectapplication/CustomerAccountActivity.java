package com.example.b07projectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.b07projectapplication.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomerAccountActivity extends AppCompatActivity {

    private EditText input_email;
    private EditText input_password;
    private EditText input_firstName;
    private EditText input_lastName;
    private Button btn_create;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Switch s;
    private EditText input_storeName;
    private boolean ownerCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_page);
        getSupportActionBar().hide();

        input_email = findViewById(R.id.editEmail);
        input_password = findViewById(R.id.editPassword);
        input_firstName = findViewById(R.id.editFirstName);
        input_lastName = findViewById(R.id.editLastName);
        TextView owner = findViewById(R.id.textView9);
        input_storeName = findViewById(R.id.editStoreName);
        s = findViewById(R.id.switch1);

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    owner.setVisibility(View.VISIBLE);
                    input_storeName.setVisibility(View.VISIBLE);
                    ownerCheck = true;
                }
                else{
                    owner.setVisibility(View.INVISIBLE);
                    input_storeName.setVisibility(View.INVISIBLE);
                    ownerCheck = false;
                }
            }
        });

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        btn_create = findViewById(R.id.c_account);

        btn_create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                authentication();
            }
        });
    }

    private void authentication(){
        String email = input_email.getText().toString().trim();
        String firstName = input_firstName.getText().toString().trim();
        String lastName = input_lastName.getText().toString().trim();
        String password = input_password.getText().toString().trim();

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if (user != null) {
                        createUser(firstName, lastName, user.getUid());
                        sendUser();
                        Toast.makeText(CustomerAccountActivity.this, "Created New Account Successfully, Please Log In", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void createUser(String firstName, String lastName, String userUID){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        String storeName = input_storeName.getText().toString().trim();

        if (ownerCheck){
            //USER IS AN OWNER

            //Firebase does not allow empty ArrayLists, so create a placeholder to solve this
            ArrayList<Product> placeHolderProducts2 = new ArrayList<>();
            Product placeHolderProduct2 = new Product("No products added!", 0);
            placeHolderProducts2.add(placeHolderProduct2);
            ArrayList<Order> placeHolderOrders2 = new ArrayList<>();
            Order placeHolderOrder2 = new Order("No orders received!","No orders placed!",placeHolderProducts2,"0","0");
            placeHolderOrders2.add(placeHolderOrder2);

            StoreOwner owner = new StoreOwner();
            owner.setUserUID( userUID );
            owner.setFirstName( firstName );
            owner.setLastName( lastName );
            owner.setOwnerCheck( true );
            owner.setStoreName( storeName );
            owner.setProducts( placeHolderProducts2 );
            ref.child("users").child(user.getUid()).setValue(owner);
        }
        else{
            //USER IS A CUSTOMER
            ArrayList<Product> placeHolderProducts = new ArrayList<>();
            Product placeHolderProduct = new Product("No products added!", 0);
            placeHolderProducts.add(placeHolderProduct);
            ArrayList<Order> placeHolderOrders = new ArrayList<>();
            Order placeHolderOrder = new Order("No orders received!","No orders placed!",placeHolderProducts,"0","0");
            placeHolderOrders.add(placeHolderOrder);

            Customer customer = new Customer();
            customer.setUserUID( userUID );
            customer.setFirstName( firstName );
            customer.setLastName( lastName );
            customer.setOwnerCheck( false );
            ref.child("users").child(user.getUid()).setValue(customer);
        }

    }

    private void sendUser(){
        Intent intent = new Intent(CustomerAccountActivity.this, LoginActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(CustomerAccountActivity.this, LoginActivity.class);
        startActivity(back);
        finish();
    }

}
