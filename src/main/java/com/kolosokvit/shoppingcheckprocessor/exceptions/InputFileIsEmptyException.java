package com.kolosokvit.shoppingcheckprocessor.exceptions;

public class InputFileIsEmptyException extends RuntimeException {
    public InputFileIsEmptyException(String message) {
        super(message);
    }
}
