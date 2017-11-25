package com.example.ashwin.htmlresponsehandling;

/**
 * Created by ashwin on 24/11/17.
 */

public class Product {

    private String title, price;

    public Product(String title, String price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return title + " : " + price;
    }
}
