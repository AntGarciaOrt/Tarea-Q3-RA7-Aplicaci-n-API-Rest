package com.example.tienda.repository;

import com.example.tienda.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductoRepository {

    private final List<Producto> productos = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(0);

    public ProductoRepository() {
        // Datos de ejemplo
        save(new Producto(null, "Funko", "Mickey Mouse", "Figura coleccionable de Mickey",
                15.99, "https://example.com/mickey.jpg", "disney", 20));
        save(new Producto(null, "Funko", "Elsa", "Figura de Elsa de Frozen",
                17.50, "https://example.com/elsa.jpg", "Disney", 15));
        save(new Producto(null, "Funko", "Darth Vader", "Figura de Darth Vader",
                19.99, "https://example.com/vader.jpg", "starwars", 10));
        save(new Producto(null, "LEGO", "Halcón Milenario", "Set de construcción",
                149.99, "https://example.com/halcon.jpg", "StarWars", 5));
        save(new Producto(null, "Hasbro", "Spiderman", "Figura articulada",
                24.95, "https://example.com/spiderman.jpg", "marvel", 30));
    }

    public List<Producto> findAll() {
        return new ArrayList<>(productos);
    }

    // Filtro por categoría ignorando mayúsculas/minúsculas
    public List<Producto> findByCategoria(String categoria) {
        return productos.stream()
                .filter(p -> p.getCategoria() != null
                        && p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public Optional<Producto> findById(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            // Alta
            producto.setId(contador.incrementAndGet());
            producto.setUuid(java.util.UUID.randomUUID());
            producto.setCreatedAt(java.time.LocalDateTime.now());
            producto.setUpdatedAt(java.time.LocalDateTime.now());
            productos.add(producto);
        } else {
            // Actualización: reemplaza el existente
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getId().equals(producto.getId())) {
                    producto.setUpdatedAt(java.time.LocalDateTime.now());
                    productos.set(i, producto);
                    break;
                }
            }
        }
        return producto;
    }

    public boolean deleteById(Long id) {
        return productos.removeIf(p -> p.getId().equals(id));
    }
}