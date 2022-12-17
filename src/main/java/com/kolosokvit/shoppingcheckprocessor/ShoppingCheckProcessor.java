package com.kolosokvit.shoppingcheckprocessor;

import com.kolosokvit.shoppingcheckprocessor.exceptions.CustomerWithNoPurchasesException;
import com.kolosokvit.shoppingcheckprocessor.exceptions.InputFileIsEmptyException;

import java.io.*;
import java.util.*;

public class ShoppingCheckProcessor {
    private List<Purchase> customerPurchases = new ArrayList<>();
    private DiscountCard customerDiscountCard = null;
    private List<String> check = new ArrayList<>();

    public void readData(String[] data) {
        if (data.length == 1 && new File(data[0]).exists()) {
            try (FileReader fileReader = new FileReader(data[0]); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line = bufferedReader.readLine();
                if (!line.isEmpty()) {
                    readData(line.split(" "));
                } else {
                    throw new InputFileIsEmptyException("Input file is empty!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            for (String s : data) {
                String[] array = s.split("-");
                if (array[0].equalsIgnoreCase("card")) {
                    customerDiscountCard = ShopDataBase.discountCards.get(array[1]);
                } else {
                    customerPurchases.add(new Purchase(ShopDataBase.products.get(Integer.parseInt(array[0])), Integer.parseInt(array[1])));
                }
            }
        }
    }

    public void generateCheck() {
        if (!customerPurchases.isEmpty()) {
            check.add("--------------------HEADER--------------------");
            check.add(Calendar.getInstance().getTime().toString());
            check.add("----------------------------------------------");
            check.add("QTY  Description              Price     Total");
            double subtotalCost = 0;
            double totalDiscount = 0;
            for (Purchase purchase : customerPurchases) {
                Product product = purchase.getProduct();
                int quantity = purchase.getQuantity();
                double price;
                if (product.isOnDiscount() && quantity >= 5 && customerDiscountCard != null && customerDiscountCard.isActive()) {
                    price = product.getPrice();
                    subtotalCost += price;

                    StringBuilder checkLine = new StringBuilder();
                    checkLine.append(quantity);
                    while (checkLine.length() < 5) {
                        checkLine.append(" ");
                    }
                    if (product.getDescription().length() > 25) {
                        checkLine.append(product.getDescription(), 0, 19);
                    } else {
                        checkLine.append(product.getDescription());
                        while (checkLine.length() < 30) {
                            checkLine.append(" ");
                        }
                    }
                    checkLine.append(price);
                    while (checkLine.length() < 41) {
                        checkLine.append(" ");
                    }
                    checkLine.append(price * quantity);
                    check.add(checkLine.toString());
                    double discount = price * quantity * 0.1;
                    totalDiscount += discount;
                    check.add("                              discount: -" + discount);
                } else {
                    price = purchase.getProduct().getPrice();
                    subtotalCost += price;
                    StringBuilder checkLine = new StringBuilder();
                    checkLine.append(quantity);
                    while (checkLine.length() < 5) {
                        checkLine.append(" ");
                    }
                    if (product.getDescription().length() > 25) {
                        checkLine.append(product.getDescription(), 0, 19);
                    } else {
                        checkLine.append(product.getDescription());
                        while (checkLine.length() < 30) {
                            checkLine.append(" ");
                        }
                    }
                    checkLine.append(price);
                    while (checkLine.length() < 41) {
                        checkLine.append(" ");
                    }
                    checkLine.append(price * quantity);
                    check.add(checkLine.toString());
                }
            }
            check.add("----------------------------------------------");
            check.add("Subtotal:                                " + subtotalCost);
            check.add("Total discount:                          " + totalDiscount);
            check.add("Total:                                   " + (subtotalCost - totalDiscount));
        } else {
            throw new CustomerWithNoPurchasesException("Customer have no purchases. Can't calculate check.");
        }
    }

    public void printCheckToConsole() {
        generateCheck();
        for (String s : check) {
            System.out.println(s);
        }
    }

    public void printCheckToFile() {
        File outputCheck = new File("check.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputCheck))) {
            for (String s : check) {
                bufferedWriter.write(s + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Purchase> getCustomerPurchases() {
        return customerPurchases;
    }

    public DiscountCard getCustomerDiscountCard() {
        return customerDiscountCard;
    }

    public static void main(String[] args) {
        String[] data = new String[] {"1-5", "3-3", "2-2", "4-4", "card-1111"};
        //String[] data = new String[] {"/home/vitali/projects/shopping-check-processor/src/main/resources/TestData.txt"};
        ShoppingCheckProcessor processor = new ShoppingCheckProcessor();
        processor.readData(data);
        processor.printCheckToConsole();
        processor.printCheckToFile();
    }
}
