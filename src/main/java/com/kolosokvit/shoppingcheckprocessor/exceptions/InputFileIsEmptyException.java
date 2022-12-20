package com.kolosokvit.shoppingcheckprocessor.exceptions;

public class InputFileIsEmptyException extends Exception {
    public InputFileIsEmptyException() {
    }

    public InputFileIsEmptyException(String message) {
        super(message);
    }

    public InputFileIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputFileIsEmptyException(Throwable cause) {
        super(cause);
    }
}
