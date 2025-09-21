package com.example.order.service;

import com.example.order.entity.OrderEntity;
import com.example.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderService {
    private final OrderRepository repo;
    private final WebClient webClient;
    private final String inventoryUrl;
    private final String notificationUrl;

    public OrderService(OrderRepository repo, WebClient.Builder builder,
                        @Value("${INVENTORY_URL:http://localhost:8081}") String inventoryUrl,
                        @Value("${NOTIFICATION_URL:http://localhost:8082}") String notificationUrl) {
        this.repo = repo;
        this.webClient = builder.build();
        this.inventoryUrl = inventoryUrl;
        this.notificationUrl = notificationUrl;
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "inventoryFallback")
    @Retry(name = "inventoryService")
    public boolean callInventory(Long productId, int qty) {
        String url = inventoryUrl + "/inventory/decrease/" + productId + "/" + qty;
        String resp = webClient.post().uri(url).retrieve().bodyToMono(String.class).block();
        return resp != null && resp.toLowerCase().contains("decreased");
    }

    public boolean inventoryFallback(Long productId, int qty, Throwable t) {
        // fallback when inventory service is down or failing
        return false;
    }

    @CircuitBreaker(name = "notificationService", fallbackMethod = "notificationFallback")
    @Retry(name = "notificationService")
    public String callNotification(String orderId, String email, String phone) {
        String url = notificationUrl + "/notifications/send?orderId=" + orderId + "&email=" + email + "&phone=" + phone;
        return webClient.post().uri(url).retrieve().bodyToMono(String.class).block();
    }

    public String notificationFallback(String orderId, String email, String phone, Throwable t) {
        return "NOTIFICATION_SERVICE_UNAVAILABLE";
    }

    public OrderEntity createOrder(OrderEntity o) {
        o.setStatus("CREATING");
        OrderEntity saved = repo.save(o);

        boolean stockOk = callInventory(saved.getProductId(), saved.getQuantity());
        if (!stockOk) {
            saved.setStatus("FAILED_NO_STOCK");
            return repo.save(saved);
        }

        String notif = callNotification(saved.getId().toString(), saved.getEmail(), saved.getPhone());
        saved.setStatus("COMPLETED: " + (notif != null ? notif : "no-notif"));
        return repo.save(saved);
    }
}
