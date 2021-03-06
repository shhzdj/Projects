package com.example.b07projectapplication.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult_AUTOGEN {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    LoginResult_AUTOGEN(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult_AUTOGEN(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}