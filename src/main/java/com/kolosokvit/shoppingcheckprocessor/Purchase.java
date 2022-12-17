package com.kolosokvit.shoppingcheckprocessor;

public class Purchase {
    private Product product;
    private int quantity;

    public Purchase(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
