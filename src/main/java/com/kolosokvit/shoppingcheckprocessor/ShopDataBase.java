package com.kolosokvit.shoppingcheckprocessor;

import java.util.HashMap;
import java.util.Map;

public class ShopDataBase {
    public static final double PROMOTIONAL_DISCOUNT = 10.0;
    public static final double LOYALTY_PROGRAM_DISCOUNT = 3.0;
    public static Map<Integer, Product> products = new HashMap<>();
    public static Map<String, DiscountCard> discountCards = new HashMap<>();

    static {
        Product product1 = new Product(1, "Milk, 1L", 2.4, true);
        Product product2 = new Product(2, "Butter, 72%", 4.25, false);
        Product product3 = new Product(3, "Bread, 0,5kg", 2.8, true);
        Product product4 = new Product(4, "Sour-cream 20%, 200g", 3.9, true);
        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);
        products.put(product4.getId(), product4);

        DiscountCard discountCard1 = new DiscountCard("1111", true);
        DiscountCard discountCard2 = new DiscountCard("2222", false);
        discountCards.put(discountCard1.getNumber(), discountCard1);
        discountCards.put(discountCard2.getNumber(), discountCard2);
    }

    private ShopDataBase() {
    }
}
