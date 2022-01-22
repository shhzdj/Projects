package com.example.b07projectapplication;

import java.text.DecimalFormat;
import java.util.Objects;

public class Product {
    String name;
    String priceString;
    double price;
    int quantity;

    public Product(){
    }

    public Product(String name, double price){
        this.name = name;
        this.price = price;
        this.priceString = String.valueOf( Math.round(this.price * 100.0)/100.0 );
    }

    public String getName() { return name; }
    public double getPrice() {
        return ( Double.parseDouble(priceString) );
    }
    public String getPriceString() {
        return ("$" + this.priceString);
    }
    public int getQuantity() { return this.quantity; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        this.priceString = decimalFormat.format(price);
        this.price = Double.parseDouble(priceString);
    }
    public void setQuantity(int quantity) { this.quantity = quantity; }


    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if ( this.getClass() != obj.getClass() )
            return false;

        Product other = (Product) obj;
        return Double.compare(other.price, price) == 0 && Objects.equals(name, other.name);
    }

}
