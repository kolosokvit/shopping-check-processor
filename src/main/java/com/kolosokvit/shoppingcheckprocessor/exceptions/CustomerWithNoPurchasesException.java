package com.kolosokvit.shoppingcheckprocessor.exceptions;

public class CustomerWithNoPurchasesException extends RuntimeException {
    public CustomerWithNoPurchasesException(String message) {
        super(message);
    }
}
