package com.kolosokvit.shoppingcheckprocessor.shop;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return isActive() == that.isActive() && getNumber().equals(that.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), isActive());
    }
}
