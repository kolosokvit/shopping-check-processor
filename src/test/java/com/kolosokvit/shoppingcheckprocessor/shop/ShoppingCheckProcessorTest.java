package com.kolosokvit.shoppingcheckprocessor.shop;

import com.kolosokvit.shoppingcheckprocessor.AppRunner;
import com.kolosokvit.shoppingcheckprocessor.exceptions.CustomerWithNoPurchasesException;
import com.kolosokvit.shoppingcheckprocessor.exceptions.InputFileIsEmptyException;
import com.kolosokvit.shoppingcheckprocessor.exceptions.ProductWithInvalidIDException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


class ShoppingCheckProcessorTest {

    @Test
    void shouldThrowInputFileIsEmptyException() {
        ShoppingCheckProcessor checkProcessor = new ShoppingCheckProcessor();
        InputFileIsEmptyException thrown = Assertions.assertThrows(InputFileIsEmptyException.class, () -> checkProcessor.readData(new String[] {"/home/vitali/projects/shopping-check-processor/src/test/resources/EmptyTestFile.txt"}));
        Assertions.assertEquals("Input file is empty!", thrown.getMessage());
    }

    @Test
    void shouldThrowProductWithInvalidIDException() {
        ShoppingCheckProcessor checkProcessor = new ShoppingCheckProcessor();
        ProductWithInvalidIDException thrown = Assertions.assertThrows(ProductWithInvalidIDException.class, () -> checkProcessor.readData(new String[] {"100-1"}));
        Assertions.assertEquals("Product with invalid ID.", thrown.getMessage());
    }

    @Test
    void shouldThrowCustomerWithNoPurchasesException() {
        ShoppingCheckProcessor checkProcessor = new ShoppingCheckProcessor();
        CustomerWithNoPurchasesException thrown = Assertions.assertThrows(CustomerWithNoPurchasesException.class, checkProcessor::generateCheck);
        Assertions.assertEquals("Customer have no purchases. Can't calculate check.", thrown.getMessage());
    }

    @Test
    void readDataFromFile() {
        ShoppingCheckProcessor checkProcessor = new ShoppingCheckProcessor();
        List<Purchase> expectedCustomerPurchases = new ArrayList<>();
        expectedCustomerPurchases.add(new Purchase(ShopDataBase.products.get(4), 5));
        expectedCustomerPurchases.add(new Purchase(ShopDataBase.products.get(3), 3));
        expectedCustomerPurchases.add(new Purchase(ShopDataBase.products.get(2), 2));
        expectedCustomerPurchases.add(new Purchase(ShopDataBase.products.get(1), 1));
        try {
            checkProcessor.readData(new String[] {"/home/vitali/projects/shopping-check-processor/src/test/resources/TestData.txt"});
            List<Purchase> actualCustomerPurchase = checkProcessor.getCustomerPurchases();
            for (int i =  0; i < actualCustomerPurchase.size(); i++) {
                Assertions.assertEquals(expectedCustomerPurchases.get(i), actualCustomerPurchase.get(i));
            }
        } catch (InputFileIsEmptyException | ProductWithInvalidIDException e) {
            e.printStackTrace();
        }

        DiscountCard actualCustomerDiscountCard = checkProcessor.getCustomerDiscountCard();
        DiscountCard expectedCustomerDiscountCard = new DiscountCard("1111", true);
        Assertions.assertEquals(expectedCustomerDiscountCard, actualCustomerDiscountCard);
    }

    @Test
    void readDataFromArgs() {
        ShoppingCheckProcessor checkProcessor = new ShoppingCheckProcessor();
        String[] args = new String[] {"1-1", "2-2", "card-1111"};
        List<Purchase> expectedCustomerPurchases = new ArrayList<>();
        expectedCustomerPurchases.add(new Purchase(ShopDataBase.products.get(1), 1));
        expectedCustomerPurchases.add(new Purchase(ShopDataBase.products.get(2), 2));
        try {
            checkProcessor.readData(args);
            List<Purchase> actualCustomerPurchase = checkProcessor.getCustomerPurchases();
            for (int i =  0; i < actualCustomerPurchase.size(); i++) {
                Assertions.assertEquals(expectedCustomerPurchases.get(i), actualCustomerPurchase.get(i));
            }
        } catch (InputFileIsEmptyException | ProductWithInvalidIDException e) {
            e.printStackTrace();
        }

        DiscountCard actualCustomerDiscountCard = checkProcessor.getCustomerDiscountCard();
        DiscountCard expectedCustomerDiscountCard = new DiscountCard("1111", true);
        Assertions.assertEquals(expectedCustomerDiscountCard, actualCustomerDiscountCard);
    }

    @Test
    void generateCheck() {
        ShoppingCheckProcessor checkProcessor = new ShoppingCheckProcessor();
        List<String> expectedCheck = new ArrayList<>();
        try {
            checkProcessor.readData(new String[]{"/home/vitali/projects/shopping-check-processor/src/test/resources/TestData.txt"});
        } catch (InputFileIsEmptyException | ProductWithInvalidIDException e) {
            e.printStackTrace();
        }
    }

    @Test
    void printCheckToConsole() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        ShoppingCheckProcessor checkProcessor = new ShoppingCheckProcessor();
        try {
            checkProcessor.readData(new String[]{"/home/vitali/projects/shopping-check-processor/src/test/resources/TestData.txt"});
        } catch (InputFileIsEmptyException | ProductWithInvalidIDException e) {
            e.printStackTrace();
        }
        checkProcessor.printCheckToConsole();
        StringBuilder expectedCheckPrintedToConsole = new StringBuilder();
        for (String s : checkProcessor.getCheck()) {
            expectedCheckPrintedToConsole.append(s);
            expectedCheckPrintedToConsole.append(System.getProperty("line.separator"));
        }
        Assertions.assertEquals(expectedCheckPrintedToConsole.toString(), outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void printCheckToFile() {
        List<String> expectedCheck = new ArrayList<>();
        expectedCheck.add("--------------------HEADER--------------------");
        expectedCheck.add("Some Date");
        expectedCheck.add("----------------------------------------------");
        expectedCheck.add("QTY  Description              Price      Total");
        expectedCheck.add("5    Sour-cream 20%, 200g     3.9        19.5");
        expectedCheck.add("                  promotional discount: -1.95");
        expectedCheck.add("3    Bread, 0,5kg             2.8        8.4");
        expectedCheck.add("2    Butter, 72%              4.25       8.5");
        expectedCheck.add("1    Milk, 1L                 2.4        2.4");
        expectedCheck.add("----------------------------------------------");
        expectedCheck.add("Subtotal:                                36.85");
        expectedCheck.add("Loyalty program discount:               -1.16");
        expectedCheck.add("Total:                                   35.69");

        AppRunner.main(new String[]{"/home/vitali/projects/shopping-check-processor/src/test/resources/TestData.txt"});
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("output/check.txt"))) {
            String actualCheckLine = null;
            int lineCounter = 0;
            while ((actualCheckLine = bufferedReader.readLine()) != null) {
                if (lineCounter != 1) { // skipping line with date
                    Assertions.assertEquals(expectedCheck.get(lineCounter), actualCheckLine);
                    lineCounter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}