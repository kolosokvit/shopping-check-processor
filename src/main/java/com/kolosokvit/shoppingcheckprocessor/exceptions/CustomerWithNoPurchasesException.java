package com.kolosokvit.shoppingcheckprocessor.exceptions;

public class CustomerWithNoPurchasesException extends Exception {
    public CustomerWithNoPurchasesException() {
    }

    public CustomerWithNoPurchasesException(String message) {
        super(message);
    }

    public CustomerWithNoPurchasesException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerWithNoPurchasesException(Throwable cause) {
        super(cause);
    }
}
