Mastering Microservices Resilience with the Circuit Breaker Pattern
Welcome to this guide on enhancing the reliability of your Spring Boot microservices using the circuit breaker pattern! In the world of distributed systems, where services like order-service, inventory-service, and notifications-service work together, failures due to network issues, service downtime, or high latency are inevitable. These challenges can ripple through your system, causing cascading failures and disrupting the user experience. But don’t worry—there’s a powerful solution to keep your microservices robust and responsive!
In this repository, I dive into the circuit breaker pattern, a game-changer for building fault-tolerant microservices. Using tools like Resilience4j or Spring Cloud Circuit Breaker, this pattern monitors service interactions, detects failures, and prevents errors from spiraling out of control by intelligently managing requests to struggling services. Whether it’s gracefully handling a downed inventory-service or ensuring the order-service stays operational, the circuit breaker pattern empowers you to create scalable, resilient architectures that thrive in unpredictable environments. Check out the code samples and insights here, and take a couple of minutes to explore my blog post for a clear, practical breakdown of how it all works. Let’s build better microservices together!

Shopping microservices example (Inventory, Notification, Order) - packaged for local build

How to use:
  1. On your machine ensure Java 17, Maven and Docker are installed.
  2. Build each project jar (optional if you want Docker to build):
     cd inventory-service && mvn clean package -DskipTests
     cd ../notification-service && mvn clean package -DskipTests
     cd ../order-service && mvn clean package -DskipTests
  3. From the root folder (where docker-compose.yml is):
     docker-compose up --build
  4. Endpoints:
     Inventory:  POST http://localhost:8081/inventory/decrease/{id}/{qty}
                 POST http://localhost:8081/inventory/products  (create product)
                 GET  http://localhost:8081/inventory/products
     Notification: POST http://localhost:8082/notifications/send?orderId=...&email=...&phone=...
     Orders: POST http://localhost:8080/orders   (body: productId,quantity,email,phone)
            GET  http://localhost:8080/orders
