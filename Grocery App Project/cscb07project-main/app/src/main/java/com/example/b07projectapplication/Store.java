package com.example.b07projectapplication;

import java.util.ArrayList;
import java.util.Objects;

public class Store {

    String storeName;
    StoreOwner storeOwner;
    ArrayList<Product> products;

    public Store(StoreOwner storeOwner) {
        this.storeName = storeOwner.storeName;
        this.storeOwner = storeOwner;
        products = new ArrayList<Product>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public String getStoreName() {
        return storeName;
    }
    public StoreOwner getStoreOwner() {
        return storeOwner;
    }

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }


    @Override
    public String toString() {
        return "Store Name: " + storeName + "\nStore Owner: " + storeOwner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeName, storeOwner, products);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(storeName, store.storeName) &&
                Objects.equals(storeOwner, store.storeOwner) &&
                Objects.equals(products, store.products);
    }

}
