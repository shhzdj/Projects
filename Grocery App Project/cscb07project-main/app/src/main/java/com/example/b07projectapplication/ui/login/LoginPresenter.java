package com.example.b07projectapplication.ui.login;

public class LoginPresenter implements LoginContract.Presenter{
    LoginContract.View view;
    LoginContract.Model model;


    public LoginPresenter(LoginContract.View view, LoginContract.Model model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void checkInput() {
        String email = view.getEmail();
        String password = view.getPassword();
        model.validateAccount(email, password);
    }

    @Override
    public void successfulLogin(boolean isOwner){
        view.displayMessage(isOwner);
        if ( isOwner ){
            view.sendUserToOwner();
        }
        else{
            view.sendUserToCustomer();
        }
    }

    @Override
    public void checkError(String error){
        if (error.equals("Database error")){
            view.displayError("Database error!");
        }
        else if (error.equals("Login error")){
            view.displayError("Login Failed, Please Check Your Email and Password!");
        }
        else if (error.equals("DNE")){
            view.displayError("User Does Not Exist!");
        }
    }

}
