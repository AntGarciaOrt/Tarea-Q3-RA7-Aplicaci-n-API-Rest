package com.example.tienda.controller;

import com.example.tienda.dto.*;
import com.example.tienda.exception.ProductoBadRequestException;
import com.example.tienda.mapper.ProductoMapper;
import com.example.tienda.model.Producto;
import com.example.tienda.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductosRestController {

    private final ProductoService service;
    private final ProductoMapper mapper;

    public ProductosRestController(ProductoService service, ProductoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ProductoResponseDto> getAll(@RequestParam(required = false) String categoria) {
        return mapper.toResponseDtoList(service.findAll(categoria));
    }

    @GetMapping("/{id}")
    public ProductoResponseDto getById(@PathVariable Long id) {
        return mapper.toResponseDto(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto create(@Valid @RequestBody ProductoCreateDto dto) {
        Producto guardado = service.save(mapper.toModel(dto));
        return mapper.toResponseDto(guardado);
    }

    @PutMapping("/{id}")
    public ProductoResponseDto update(@PathVariable Long id,
                                      @Valid @RequestBody ProductoUpdateDto dto) {
        Producto existente = service.findById(id);
        Producto actualizado = service.update(id, mapper.toModel(dto, existente));
        return mapper.toResponseDto(actualizado);
    }

    @PatchMapping("/{id}")
    public ProductoResponseDto partialUpdate(@PathVariable Long id,
                                             @Valid @RequestBody ProductoPatchDto dto) {
        if (dto.isEmpty()) {
            throw new ProductoBadRequestException("Debes enviar al menos un campo para actualizar");
        }
        Producto existente = service.findById(id);
        Producto actualizado = service.update(id, mapper.toModel(dto, existente));
        return mapper.toResponseDto(actualizado);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return Map.of("mensaje", "Producto " + id + " borrado correctamente");
    }
}