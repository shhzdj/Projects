package com.example.b07projectapplication;

import java.util.ArrayList;

public class StoreOwner extends Person{
    String storeName;
    ArrayList<Product> products;


    public StoreOwner(){
    }

    public StoreOwner(String userUID, String firstName, String lastName, String storeName, boolean isOwner){
        super(userUID, firstName, lastName, isOwner);
        this.storeName = storeName;
    }

    public void setStoreName(String storeName) { this.storeName = storeName; }
    public String getStoreName() { return this.storeName; }

    public void setProducts(ArrayList<Product> products){
        this.products = new ArrayList<Product>();
        for (Product product: products){
            this.products.add(product);
        }
    }
    public ArrayList<Product> getProducts(){
        return new ArrayList<Product>(products);
    }


    public boolean addProduct(Product product){
        //Add a product, returns true on success, false if duplicate
        if (products.contains(product)){
            return false;
        }
        products.add(product);
        return true;
    }

    public boolean removeProduct(Product product){
        //Remove a product, returns true on success, false if product does not exist
        if ( !products.contains(product) ){
            return false;
        }
        products.remove(product);
        return true;
    }

}
