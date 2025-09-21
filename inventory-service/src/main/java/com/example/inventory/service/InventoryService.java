package com.example.inventory.service;

import com.example.inventory.entity.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    private final ProductRepository repo;

    public InventoryService(ProductRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public boolean decreaseStock(Long productId, int qty) {
        Product p = repo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        if (p.getStock() >= qty) {
            p.setStock(p.getStock() - qty);
            repo.save(p);
            return true;
        }
        return false;
    }

    public Product createSample(String name, int stock) {
        return repo.save(new Product(name, stock));
    }
}
