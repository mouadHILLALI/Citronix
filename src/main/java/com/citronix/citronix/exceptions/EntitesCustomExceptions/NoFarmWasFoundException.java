package com.citronix.citronix.exceptions.EntitesCustomExceptions;

public class NoFarmWasFoundException extends RuntimeException {
    public NoFarmWasFoundException() {
        super();
    }
    public NoFarmWasFoundException(String message) {
        super(message);
    }
}
