package com.example.b07projectapplication.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.b07projectapplication.data.LoginDataSource_AUTOGEN;
import com.example.b07projectapplication.data.LoginRepository_AUTOGEN;

/**
 * ViewModel provider factory to instantiate LoginViewModel_AUTOGEN.
 * Required given LoginViewModel_AUTOGEN has a non-empty constructor
 */
public class LoginViewModelFactory_AUTOGEN implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel_AUTOGEN.class)) {
            return (T) new LoginViewModel_AUTOGEN(LoginRepository_AUTOGEN.getInstance(new LoginDataSource_AUTOGEN()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}