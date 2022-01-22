package com.example.b07projectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.b07projectapplication.ui.login.LoginActivity;

public class CustomerHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_homepage);
        getSupportActionBar().hide();
    }

    public void sendToViewStores(View view){
        Intent intent = new Intent(CustomerHomePage.this, Customer_ViewMyStores.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        ImageButton button = (ImageButton) findViewById(R.id.btnViewStoresCustomer);
        startActivity(intent);
    }

    public void sendToViewProfile(View view){
        Intent intent = new Intent(CustomerHomePage.this, Customer_ViewProfile.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        ImageButton button = (ImageButton) findViewById(R.id.btnViewProfileCustomer);
        startActivity(intent);
    }

    public void sendToViewOrder(View view){
        Intent intent = new Intent(CustomerHomePage.this, Customer_ViewOrder.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        ImageButton button = (ImageButton) findViewById(R.id.btnViewOrdersCustomer);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CustomerHomePage.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
