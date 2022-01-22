package com.example.b07projectapplication.ui.login;

import androidx.annotation.NonNull;

import com.example.b07projectapplication.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginModel implements LoginContract.Model {
    LoginContract.Presenter presenter;

    public LoginModel(){
    }

    @Override
    public void validateAccount(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    FirebaseUser user = task.getResult().getUser();
                    String userUID = user.getUid();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

                    ref.child(userUID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()){
                                Person person = task.getResult().getValue(Person.class);
                                if (person != null) {
                                    boolean isOwner = person.getOwnerCheck();

                                    if (isOwner)
                                        //User is an owner
                                        presenter.successfulLogin(true);
                                    else
                                        //User is a customer
                                        presenter.successfulLogin(false);

                                }
                                else
                                    //Account does not exist
                                    presenter.checkError("DNE");
                            }
                            else
                                //Database error
                                presenter.checkError("Database error");
                        }
                    });

                }
                else
                    //Wrong email and/or password
                    presenter.checkError("Login error");
            }
        });

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
