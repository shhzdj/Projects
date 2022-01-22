package com.example.b07projectapplication.data;

import com.example.b07projectapplication.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository_AUTOGEN {

    private static volatile LoginRepository_AUTOGEN instance;

    private LoginDataSource_AUTOGEN dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository_AUTOGEN(LoginDataSource_AUTOGEN dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository_AUTOGEN getInstance(LoginDataSource_AUTOGEN dataSource) {
        if (instance == null) {
            instance = new LoginRepository_AUTOGEN(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result_AUTOGEN<LoggedInUser> login(String username, String password) {
        // handle login
        Result_AUTOGEN<LoggedInUser> resultAUTOGEN = dataSource.login(username, password);
        if (resultAUTOGEN instanceof Result_AUTOGEN.Success) {
            setLoggedInUser(((Result_AUTOGEN.Success<LoggedInUser>) resultAUTOGEN).getData());
        }
        return resultAUTOGEN;
    }
}