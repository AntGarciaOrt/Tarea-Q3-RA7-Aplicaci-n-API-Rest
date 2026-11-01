package com.example.tienda.exception;

public class ProductoNotFoundException extends ProductoException {
    public ProductoNotFoundException(Long id) {
        super("Producto con id " + id + " no encontrado");
    }
}