package com.example.tienda.service;

import com.example.tienda.exception.ProductoNotFoundException;
import com.example.tienda.model.Producto;
import com.example.tienda.repository.ProductoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producto> findAll(String categoria) {
        if (categoria != null && !categoria.isBlank()) {
            return repository.findByCategoria(categoria);
        }
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "productos", key = "#id")
    public Producto findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @Override
    @CachePut(value = "productos", key = "#result.id")
    public Producto save(Producto producto) {
        producto.setId(null);
        return repository.save(producto);
    }

    @Override
    @CachePut(value = "productos", key = "#id")
    public Producto update(Long id, Producto producto) {
        repository.findById(id).orElseThrow(() -> new ProductoNotFoundException(id));
        producto.setId(id);
        return repository.save(producto);
    }

    @Override
    @CacheEvict(value = "productos", key = "#id")
    public void deleteById(Long id) {
        if (!repository.deleteById(id)) {
            throw new ProductoNotFoundException(id);
        }
    }
}