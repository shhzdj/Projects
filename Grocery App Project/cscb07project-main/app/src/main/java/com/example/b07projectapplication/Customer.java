package com.example.b07projectapplication;


public class Customer extends Person{

    public Customer(){
    }

    public Customer(String userUID, String firstName, String lastName, boolean isOwner){
        super(userUID, firstName, lastName, isOwner);
    }

}
