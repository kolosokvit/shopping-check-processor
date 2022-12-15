package com.kolosokvit.shoppingcheckprocessor;

public class Product {
    private int id;
    private String description;
    private double price;
    private boolean isOnDiscount;

    public Product(int id, String description, double price, boolean isOnDiscount) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.isOnDiscount = isOnDiscount;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOnDiscount() {
        return isOnDiscount;
    }
}
