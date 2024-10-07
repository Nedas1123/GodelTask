package com.example.demo.Errors;

public class UrlShorteningException extends RuntimeException{
    public UrlShorteningException(String message) {
        super(message);
    }
}
