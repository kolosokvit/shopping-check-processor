package com.kolosokvit.shoppingcheckprocessor.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountCalculatorTest {
    int quantity = 5;
    double price = 20.0;
    double promotionalDiscountPercent = 10.0;
    double loyaltyProgramDiscountPercent = 10;
    @Test
    void calculatePromotionalDiscount() {
        double expectedPromotionalDiscount = 10.0;
        Assertions.assertEquals(expectedPromotionalDiscount, DiscountCalculator.calculatePromotionalDiscount(quantity, price, promotionalDiscountPercent));
    }

    @Test
    void calculateLoyaltyProgramDiscount() {
        double expectedLoyaltyProgramDiscount = 10.0;
        Assertions.assertEquals(expectedLoyaltyProgramDiscount, DiscountCalculator.calculatePromotionalDiscount(quantity, price, loyaltyProgramDiscountPercent));
    }
}