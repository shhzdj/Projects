package com.example.b07projectapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class StoreOwner_AddNewProduct extends AppCompatActivity {

    private EditText productName;
    private EditText productPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_add_new_product);
        getSupportActionBar().hide();

        productName = findViewById(R.id.editProductName);
        productPrice = findViewById(R.id.editProductPrice);

    }

    public void sentToViewProducts(View view){
        Intent intent = new Intent(StoreOwner_AddNewProduct.this, StoreOwner_ViewProducts.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        addProduct();
        startActivity(intent);
    }

    private void addProduct(){

        String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        ref.child(userUID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if ( task.isSuccessful() ){
                    //READ FROM DATABASE AND CREATE A COPY OF THE ARRAYLIST
                    StoreOwner owner = task.getResult().getValue(StoreOwner.class);

                    //CREATE NEW PRODUCT
                    String newProductName = productName.getText().toString().trim();
                    String newProductPriceString = productPrice.getText().toString().trim();
                    double newProductPrice = Double.parseDouble(newProductPriceString);

                    Product newProduct = new Product();
                    newProduct.setName(newProductName);
                    newProduct.setPrice(newProductPrice);


                    //ADD NEW PRODUCT AND UPDATE THE DATABASE
                    owner.addProduct(newProduct);
                    //Remove the placeholder product created on account creation
                    Product placeHolderProduct = new Product("No products added!", 0);
                    if (owner.products.contains(placeHolderProduct)) {
                        owner.removeProduct(placeHolderProduct);
                    }
                    ref.child(userUID).setValue(owner);

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(StoreOwner_AddNewProduct.this, StoreOwner_ViewProducts.class);
        startActivity(back);
        finish();
    }

}