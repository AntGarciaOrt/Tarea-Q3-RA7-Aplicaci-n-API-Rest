package com.example.tienda.dto;

import jakarta.validation.constraints.*;

public record ProductoPatchDto(
        @Size(min = 1, message = "La marca no puede estar vacía") String marca,
        @Size(min = 1, message = "El modelo no puede estar vacío") String modelo,
        String descripcion,
        @Positive(message = "El precio debe ser mayor que 0") Double precio,
        String imagen,
        @Size(min = 1, message = "La categoría no puede estar vacía") String categoria,
        @Min(value = 0, message = "El stock no puede ser negativo") Integer stock
) {
    public boolean isEmpty() {
        return marca == null && modelo == null && descripcion == null && precio == null
                && imagen == null && categoria == null && stock == null;
    }
}