package com.kolosokvit.shoppingcheckprocessor;

import com.kolosokvit.shoppingcheckprocessor.exceptions.CustomerWithNoPurchasesException;
import com.kolosokvit.shoppingcheckprocessor.exceptions.InputFileIsEmptyException;
import com.kolosokvit.shoppingcheckprocessor.exceptions.ProductWithInvalidIDException;
import com.kolosokvit.shoppingcheckprocessor.shop.ShoppingCheckProcessor;

public class AppRunner {
    public static void main(String[] args) {
        ShoppingCheckProcessor processor = new ShoppingCheckProcessor();
        try {
            processor.readData(args);
            processor.printCheckToConsole();
            processor.printCheckToFile();
        } catch (InputFileIsEmptyException | ProductWithInvalidIDException | CustomerWithNoPurchasesException e) {
            e.printStackTrace();
        }
    }
}
