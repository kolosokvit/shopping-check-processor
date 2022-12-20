package com.kolosokvit.shoppingcheckprocessor.shop;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return getQuantity() == purchase.getQuantity() && getProduct().equals(purchase.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getQuantity());
    }
}
