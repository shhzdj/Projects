package com.example.b07projectapplication.ui.login;

public interface LoginContract {

    public interface Model{
        public void validateAccount(String email, String password);
        public void setPresenter(LoginContract.Presenter presenter);
    }

    public interface View{
        public void sendUserToCustomer();
        public void sendUserToOwner();
        public String getEmail();
        public String getPassword();
        public void displayMessage(boolean isOwner);
        public void displayError(String error);
    }

    public interface Presenter{
        public void checkInput();
        public void successfulLogin(boolean isOwner);
        public void checkError(String error);
    }
}
