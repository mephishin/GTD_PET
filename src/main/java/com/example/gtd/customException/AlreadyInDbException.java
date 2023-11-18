package com.example.gtd.customException;

public class AlreadyInDbException extends Exception {

    public AlreadyInDbException(String message) {
        super(message);
    }
}
