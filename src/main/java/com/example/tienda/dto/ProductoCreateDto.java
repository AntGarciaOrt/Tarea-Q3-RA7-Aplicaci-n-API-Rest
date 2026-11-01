package com.example.tienda.dto;

import jakarta.validation.constraints.*;

public record ProductoCreateDto(
        @NotBlank(message = "La marca no puede estar vacía") String marca,
        @NotBlank(message = "El modelo no puede estar vacío") String modelo,
        String descripcion,
        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor que 0") Double precio,
        String imagen,
        @NotBlank(message = "La categoría no puede estar vacía") String categoria,
        @NotNull(message = "El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo") Integer stock
) {}