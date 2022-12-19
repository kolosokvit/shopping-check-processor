package com.kolosokvit.shoppingcheckprocessor.utils;

public class DiscountCalculator {
    private DiscountCalculator() {
    }
    public static double calculatePromotionalDiscount(int quantity, double price, double discountPercent) {
        double discount = quantity * price * discountPercent / 100;
        return NumberRounder.roundDouble(discount);
    }

    public static double calculateLoyaltyProgramDiscount(double price, double discountPercent) {
        double loyaltyProgramDiscount = price * discountPercent / 100;
        return NumberRounder.roundDouble(loyaltyProgramDiscount);
    }
}
