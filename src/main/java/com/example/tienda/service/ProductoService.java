package com.example.tienda.service;

import com.example.tienda.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll(String categoria);
    Producto findById(Long id);
    Producto save(Producto producto);
    Producto update(Long id, Producto producto);
    void deleteById(Long id);
}