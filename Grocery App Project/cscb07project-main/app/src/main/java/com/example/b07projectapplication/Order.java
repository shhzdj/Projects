package com.example.b07projectapplication;

import java.util.ArrayList;
import java.util.Objects;

public class Order {

    String ownerUID;        //same as storeUID
    String customerUID;
    String customerName;
    String storeName;
    boolean isComplete;
    ArrayList<Product> products;

    public Order(){
    }

    public Order(String customerName, String storeName, ArrayList<Product> products, String ownerUID, String customerUID){
        this.customerName = customerName;
        this.storeName = storeName;
        this.products = new ArrayList<>();
        for (Product product: products){
            this.products.add(product);
        }
        this.customerUID = customerUID;
        this.ownerUID = ownerUID;
    }

    public String getOwnerUID() { return ownerUID; }
    public void setOwnerUID(String ownerUID) { this.ownerUID = ownerUID;}

    public String getCustomerUID() { return customerUID; }
    public void setCustomerUID(String customerUID) { this.customerUID = customerUID; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public boolean getIsComplete() { return isComplete; }
    public void setIsComplete(boolean isComplete) { this.isComplete = isComplete; }

    public ArrayList<Product> getProducts() { return products; }
    public void setProducts(ArrayList<Product> products) {
        this.products = new ArrayList<Product>();
        for (Product product: products){
            this.products.add(product);
        }
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

    @Override
    public int hashCode() {
        return Objects.hash(ownerUID, customerUID, products);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if ( this.getClass() != obj.getClass() )
            return false;

        Order other = (Order) obj;
        return Objects.equals(ownerUID, other.ownerUID) && Objects.equals(customerUID, other.customerUID) &&
                Objects.equals(customerName, other.customerName) && Objects.equals(products, other.products) &&
                Objects.equals(isComplete, other.isComplete);
    }

}
