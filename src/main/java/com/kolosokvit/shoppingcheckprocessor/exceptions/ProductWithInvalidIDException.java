package com.kolosokvit.shoppingcheckprocessor.exceptions;

public class ProductWithInvalidIDException extends Exception{
    public ProductWithInvalidIDException() {
    }

    public ProductWithInvalidIDException(String message) {
        super(message);
    }

    public ProductWithInvalidIDException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductWithInvalidIDException(Throwable cause) {
        super(cause);
    }
}
