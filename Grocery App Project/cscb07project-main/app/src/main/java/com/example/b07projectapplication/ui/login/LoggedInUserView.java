package com.example.b07projectapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.example.b07projectapplication.R;


class LoggedInUserView {
    private String displayName;

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }

    public static class CustomerCreateAccountActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_account_page);
            Intent intent = getIntent();
        }
    }

}