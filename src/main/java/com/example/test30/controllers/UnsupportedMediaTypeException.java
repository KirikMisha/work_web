package com.example.test30.controllers;

public class UnsupportedMediaTypeException extends Throwable {
    public String getContentType() {
        return null;
    }
}
