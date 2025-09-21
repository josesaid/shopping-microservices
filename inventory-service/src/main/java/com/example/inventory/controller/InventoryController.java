package com.example.inventory.controller;

import com.example.inventory.entity.Product;
import com.example.inventory.service.InventoryService;
import com.example.inventory.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService service;
    private final ProductRepository repo;

    public InventoryController(InventoryService service, ProductRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping("/decrease/{id}/{qty}")
    public ResponseEntity<String> decreaseStock(@PathVariable Long id, @PathVariable int qty) {
        boolean ok = service.decreaseStock(id, qty);
        return ok ? ResponseEntity.ok("Stock decreased") : ResponseEntity.badRequest().body("Not enough stock");
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product p) {
        return ResponseEntity.ok(repo.save(p));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(repo.findAll());
    }
}
