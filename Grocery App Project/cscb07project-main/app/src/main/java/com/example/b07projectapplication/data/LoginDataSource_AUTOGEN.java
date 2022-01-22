package com.example.b07projectapplication.data;

import com.example.b07projectapplication.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource_AUTOGEN {

    public Result_AUTOGEN<LoggedInUser> login(String username, String password) {

        try {
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result_AUTOGEN.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result_AUTOGEN.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
    }
}