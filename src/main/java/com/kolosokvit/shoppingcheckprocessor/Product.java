package com.kolosokvit.shoppingcheckprocessor;

public class Product {
    private int id;
    private String description;
    private double price;
    private boolean promotionalProduct;

    public Product(int id, String description, double price, boolean promotionalProduct) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.promotionalProduct = promotionalProduct;
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

    public boolean isPromotionalProduct() {
        return promotionalProduct;
    }
}
