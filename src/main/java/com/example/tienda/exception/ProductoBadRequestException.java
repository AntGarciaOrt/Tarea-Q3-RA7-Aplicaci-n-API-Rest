package com.example.tienda.exception;

public class ProductoBadRequestException extends ProductoException {
    public ProductoBadRequestException(String message) {
        super(message);
    }
}