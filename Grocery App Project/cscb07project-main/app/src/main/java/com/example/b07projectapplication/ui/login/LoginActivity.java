package com.example.b07projectapplication.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07projectapplication.CustomerAccountActivity;
import com.example.b07projectapplication.CustomerHomePage;
import com.example.b07projectapplication.R;
import com.example.b07projectapplication.StoreOwnerHomepage;
import com.example.b07projectapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginViewModel_AUTOGEN loginViewModelAUTOGEN;
    private ActivityLoginBinding binding;
    LoginContract.Presenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        LoginContract.Model model = new LoginModel();
        presenter = new LoginPresenter(this, model);
        model.setPresenter(presenter);



        /**--------------------------------------------START OF AUTO-GENERATED CODE------------------------------------------------------------*/
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModelAUTOGEN = new ViewModelProvider(this, new LoginViewModelFactory_AUTOGEN())
                .get(LoginViewModel_AUTOGEN.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.btnLogin;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModelAUTOGEN.getLoginFormState().observe(this, new Observer<LoginFormState_AUTOGEN>() {
            @Override
            public void onChanged(@Nullable LoginFormState_AUTOGEN loginFormStateAUTOGEN) {
                if (loginFormStateAUTOGEN == null) {
                    return;
                }
                loginButton.setEnabled(loginFormStateAUTOGEN.isDataValid());
                if (loginFormStateAUTOGEN.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormStateAUTOGEN.getUsernameError()));
                }
                if (loginFormStateAUTOGEN.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormStateAUTOGEN.getPasswordError()));
                }
            }
        });


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModelAUTOGEN.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        /**----------------------------------------------END OF AUTO-GENERATED CODE-----------------------------------------------------------------------*/


        //START LOGIN PROCESS ON ENTER PRESS
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    validateLogin();
                }
                return false;
            }
        });

    }


    public void loginButtonClick(View view){
        validateLogin();
    }

    public void validateLogin(){
        presenter.checkInput();
    }

    @Override
    public String getEmail(){
        EditText input_email = findViewById(R.id.username);
        return input_email.getText().toString();
    }

    @Override
    public String getPassword(){
        EditText input_password = findViewById(R.id.password);
        return input_password.getText().toString();
    }

    @Override
    public void sendUserToCustomer(){
        Intent intent = new Intent(LoginActivity.this, CustomerHomePage.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void sendUserToOwner(){
        Intent intent = new Intent(LoginActivity.this, StoreOwnerHomepage.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void displayMessage(boolean isOwner){
        if ( isOwner ){
            Toast.makeText(LoginActivity.this, "Logged in Successfully AS OWNER", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(LoginActivity.this, "Logged in Successfully AS CUSTOMER", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayError(String error){
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
    }


    public void sendUserToCreateAccount(View view){
        Intent intent = new Intent (this, CustomerAccountActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}