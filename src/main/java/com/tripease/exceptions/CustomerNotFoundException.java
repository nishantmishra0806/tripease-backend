package com.tripease.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
