package com.example.b07projectapplication.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.b07projectapplication.data.LoginRepository_AUTOGEN;
import com.example.b07projectapplication.data.Result_AUTOGEN;
import com.example.b07projectapplication.data.model.LoggedInUser;
import com.example.b07projectapplication.R;

public class LoginViewModel_AUTOGEN extends ViewModel {

    private MutableLiveData<LoginFormState_AUTOGEN> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult_AUTOGEN> loginResult = new MutableLiveData<>();
    private LoginRepository_AUTOGEN loginRepositoryAUTOGEN;

    LoginViewModel_AUTOGEN(LoginRepository_AUTOGEN loginRepositoryAUTOGEN) {
        this.loginRepositoryAUTOGEN = loginRepositoryAUTOGEN;
    }

    LiveData<LoginFormState_AUTOGEN> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult_AUTOGEN> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result_AUTOGEN<LoggedInUser> resultAUTOGEN = loginRepositoryAUTOGEN.login(username, password);

        if (resultAUTOGEN instanceof Result_AUTOGEN.Success) {
            LoggedInUser data = ((Result_AUTOGEN.Success<LoggedInUser>) resultAUTOGEN).getData();
            loginResult.setValue(new LoginResult_AUTOGEN(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult_AUTOGEN(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState_AUTOGEN(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState_AUTOGEN(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState_AUTOGEN(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}