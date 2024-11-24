package com.citronix.citronix.exceptions;

public class NoFarmWasFoundException extends RuntimeException {
    public NoFarmWasFoundException() {
        super();
    }
    public NoFarmWasFoundException(String message) {
        super(message);
    }
}
