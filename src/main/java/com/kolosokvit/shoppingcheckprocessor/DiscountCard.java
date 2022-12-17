package com.kolosokvit.shoppingcheckprocessor;

public class DiscountCard {
    private String number;
    private boolean isActive;

    public DiscountCard(String number, boolean isActive) {
        this.number = number;
        this.isActive = isActive;
    }

    public String getNumber() {
        return number;
    }

    public boolean isActive() {
        return isActive;
    }
}
