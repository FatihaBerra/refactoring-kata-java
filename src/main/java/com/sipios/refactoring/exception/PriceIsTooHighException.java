package com.sipios.refactoring.exception;

public class PriceIsTooHighException extends RuntimeException {

    public PriceIsTooHighException(double price, String customerType) {
        super("Price (" + price + ") is too high for (" + customerType + ") customer");
    }
}
