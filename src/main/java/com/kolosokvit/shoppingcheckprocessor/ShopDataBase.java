package com.kolosokvit.shoppingcheckprocessor;

import java.util.HashMap;
import java.util.Map;

public class ShopDataBase {
    public static Map<Integer, Product> products = new HashMap<>();

    static {
        Product milk = new Product(1, "Milk, 1L", 2.50, false);
        Product butter = new Product(2, "Butter, 72%", 3.10, false);
        Product bread = new Product(3, "Bread, 0,5kg", 1.80, false);
        Product sourCream = new Product(4, "Sour-cream, 20%", 2.80, true);

        products.put(milk.getId(), milk);
        products.put(butter.getId(), butter);
        products.put(bread.getId(), bread);
        products.put(sourCream.getId(), sourCream);
    }

    private ShopDataBase() {
    }
}
