package com.example.tienda.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Producto {

    private Long id;
    private String marca;
    private String modelo;
    private String descripcion;
    private Double precio;
    private String imagen;
    private String categoria;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID uuid;

    public Producto() {
    }

    public Producto(Long id, String marca, String modelo, String descripcion,
                    Double precio, String imagen, String categoria, Integer stock) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
        this.stock = stock;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.uuid = UUID.randomUUID();
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public UUID getUuid() { return uuid; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }
}