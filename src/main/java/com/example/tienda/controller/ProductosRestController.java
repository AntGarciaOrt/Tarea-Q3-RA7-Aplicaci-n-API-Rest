package com.example.tienda.controller;

import com.example.tienda.model.Producto;
import com.example.tienda.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductosRestController {

    private final ProductoRepository repository;

    public ProductosRestController(ProductoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Producto> getAll(@RequestParam(required = false) String categoria) {
        if (categoria != null && !categoria.isBlank()) {
            return repository.findByCategoria(categoria);
        }
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto con id " + id + " no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto create(@RequestBody Producto producto) {
        producto.setId(null);
        return repository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto nuevo) {
        Producto existente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto con id " + id + " no encontrado"));

        nuevo.setId(id);
        nuevo.setUuid(existente.getUuid());
        nuevo.setCreatedAt(existente.getCreatedAt());
        return repository.save(nuevo);
    }

    @PatchMapping("/{id}")
    public Producto partialUpdate(@PathVariable Long id, @RequestBody Producto cambios) {
        Producto existente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto con id " + id + " no encontrado"));

        if (cambios.getMarca() != null)       existente.setMarca(cambios.getMarca());
        if (cambios.getModelo() != null)      existente.setModelo(cambios.getModelo());
        if (cambios.getDescripcion() != null) existente.setDescripcion(cambios.getDescripcion());
        if (cambios.getPrecio() != null)      existente.setPrecio(cambios.getPrecio());
        if (cambios.getImagen() != null)      existente.setImagen(cambios.getImagen());
        if (cambios.getCategoria() != null)   existente.setCategoria(cambios.getCategoria());
        if (cambios.getStock() != null)       existente.setStock(cambios.getStock());

        return repository.save(existente);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        boolean borrado = repository.deleteById(id);
        if (!borrado) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto con id " + id + " no encontrado");
        }
        return Map.of("mensaje", "Producto " + id + " borrado correctamente");
    }
}