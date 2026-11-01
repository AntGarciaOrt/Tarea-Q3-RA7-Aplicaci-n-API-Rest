package com.example.tienda.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductoResponseDto(
        Long id,
        UUID uuid,
        String marca,
        String modelo,
        String descripcion,
        Double precio,
        String imagen,
        String categoria,
        Integer stock,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}