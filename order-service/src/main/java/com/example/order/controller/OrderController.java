package com.example.order.controller;

import com.example.order.entity.OrderEntity;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService svc;
    private final OrderRepository repo;

    public OrderController(OrderService svc, OrderRepository repo) {
        this.svc = svc;
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<OrderEntity> create(@RequestBody OrderEntity o) {
        return ResponseEntity.ok(svc.createOrder(o));
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> list() {
        return ResponseEntity.ok(repo.findAll());
    }
}
