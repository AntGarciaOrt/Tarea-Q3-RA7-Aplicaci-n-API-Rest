package com.example.tienda.mapper;

import com.example.tienda.dto.*;
import com.example.tienda.model.Producto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductoMapper {

    // POST:
    public Producto toModel(ProductoCreateDto dto) {
        return new Producto(null, dto.marca(), dto.modelo(), dto.descripcion(),
                dto.precio(), dto.imagen(), dto.categoria(), dto.stock());
    }

    // PUT:
    public Producto toModel(ProductoUpdateDto dto, Producto existente) {
        Producto p = new Producto(existente.getId(), dto.marca(), dto.modelo(), dto.descripcion(),
                dto.precio(), dto.imagen(), dto.categoria(), dto.stock());
        p.setUuid(existente.getUuid());
        p.setCreatedAt(existente.getCreatedAt());
        return p;
    }

    // PATCH:
    public Producto toModel(ProductoPatchDto dto, Producto existente) {
        Producto p = new Producto(
                existente.getId(),
                dto.marca() != null ? dto.marca() : existente.getMarca(),
                dto.modelo() != null ? dto.modelo() : existente.getModelo(),
                dto.descripcion() != null ? dto.descripcion() : existente.getDescripcion(),
                dto.precio() != null ? dto.precio() : existente.getPrecio(),
                dto.imagen() != null ? dto.imagen() : existente.getImagen(),
                dto.categoria() != null ? dto.categoria() : existente.getCategoria(),
                dto.stock() != null ? dto.stock() : existente.getStock());
        p.setUuid(existente.getUuid());
        p.setCreatedAt(existente.getCreatedAt());
        return p;
    }

    public ProductoResponseDto toResponseDto(Producto p) {
        return new ProductoResponseDto(p.getId(), p.getUuid(), p.getMarca(), p.getModelo(),
                p.getDescripcion(), p.getPrecio(), p.getImagen(), p.getCategoria(),
                p.getStock(), p.getCreatedAt(), p.getUpdatedAt());
    }

    public List<ProductoResponseDto> toResponseDtoList(List<Producto> productos) {
        return productos.stream().map(this::toResponseDto).toList();
    }
}