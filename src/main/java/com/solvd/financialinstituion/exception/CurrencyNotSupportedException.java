package com.solvd.financialinstituion.exception;

public class CurrencyNotSupportedException extends RuntimeException {

    public CurrencyNotSupportedException(String message) {
        super(message);
    }
}
