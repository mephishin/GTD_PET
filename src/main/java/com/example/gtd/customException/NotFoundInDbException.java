package com.example.gtd.customException;



public class NotFoundInDbException extends Exception{

    public NotFoundInDbException(String message) {
        super(message);
    }
}
