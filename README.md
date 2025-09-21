<xaiArtifact artifact_id="01259f6b-5688-4d50-86e8-58f4176e202f" artifact_version_id="716d2407-3f05-43dc-8213-d292fc2c3c8a" title="README.md" contentType="text/markdown">

# Projects

## Mastering Microservices Resilience with the Circuit Breaker Pattern

Welcome to this guide on enhancing the reliability of your **Spring Boot microservices** using the **Circuit Breaker Pattern**! In the world of distributed systems, where services like `order-service`, `inventory-service`, and `notifications-service` work together, failures due to network issues, service downtime, or high latency are inevitable. These challenges can ripple through your system, causing cascading failures and disrupting the user experience. But don’t worry—there’s a powerful solution to keep your microservices robust and responsive!

In this repository, I dive into the **Circuit Breaker Pattern**, a game-changer for building fault-tolerant microservices. Using tools like **Resilience4j** or **Spring Cloud Circuit Breaker**, this pattern monitors service interactions, detects failures, and prevents errors from spiraling out of control by intelligently managing requests to struggling services. Whether it’s gracefully handling a downed `inventory-service` or ensuring the `order-service` stays operational, the circuit breaker pattern empowers you to create scalable, resilient architectures that thrive in unpredictable environments.

Check out the [code samples](https://github.com/josesaid) and insights, and take a couple of minutes to explore my [blog post](https://josesaid08.s3.us-east-1.amazonaws.com/site/index.html) for a clear, practical breakdown of how it all works. Let’s build better microservices together!

### Shopping Microservices Example (Inventory, Notification, Order)

Packaged for local build.

#### How to Use

1. Ensure **Java 17**, **Maven**, and **Docker** are installed on your machine.
2. Build each project JAR (optional if you want Docker to build):
   ```bash
   cd inventory-service && mvn clean package -DskipTests
   cd ../notification-service && mvn clean package -DskipTests
   cd ../order-service && mvn clean package -DskipTests
   ```
3. From the root folder (where `docker-compose.yml` is):
   ```bash
   docker-compose up --build
   ```

#### Endpoints

- **Inventory**:
  - `POST http://localhost:8081/inventory/decrease/{id}/{qty}`
  - `POST http://localhost:8081/inventory/products` (create product)
  - `GET http://localhost:8081/inventory/products`
- **Notification**:
  - `POST http://localhost:8082/notifications/send?orderId=...&email=...&phone=...`
- **Orders**:
  - `POST http://localhost:8080/orders` (body: `productId`, `quantity`, `email`, `phone`)
  - `GET http://localhost:8080/orders`

Explore more on my [GitHub repositories](https://github.com/josesaid) and [personal site](https://josesaid08.s3.us-east-1.amazonaws.com/site/index.html).

</xaiArtifact>
