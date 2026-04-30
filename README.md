# Order Management System (Java 21 + Spring Boot + Kafka)

Sistema de gestión de órdenes basado en **arquitectura de microservicios** con comunicación asíncrona usando **Apache Kafka**.
  
---

##  Descripción

La aplicación permite:

* Crear órdenes de compra
* Persistir datos en base de datos H2
* Publicar eventos en Kafka (`order-created`)
* Procesar eventos en un microservicio independiente (Notification Service)
* Proteger endpoints con Spring Security
* Documentar la API con Swagger
* Validar comportamiento con pruebas unitarias

---

##  Arquitectura

```
          ┌────────────────────┐
          │   API Gateway      │
          │ (Spring Cloud)     │
          └─────────┬──────────┘
                    │
        ┌───────────┴────────────┐
        │                        │
┌───────────────┐       ┌────────────────────┐
│ Order Service │       │ Notification Svc   │
│ Spring Boot   │       │ Spring Boot        │
│ JDBC + H2     │       │ Kafka Consumer     │
└──────┬────────┘       └─────────┬──────────┘
       │                          │
       └──── Kafka Producer ──────┘
                 │
        ┌────────────────────┐
        │     Kafka Topic    │
        │   order-created    │
        └────────────────────┘
```

---

## Tecnologías utilizadas

* Java 21
* Spring Boot 3
* Spring Security
* Spring JDBC
* H2 Database
* Apache Kafka
* Spring Cloud (Gateway / Config)
* OpenAPI (Swagger)
* JUnit 5 + Mockito
* Lombok

---

##  Estructura del proyecto

```
order-service/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├── kafka/
 ├── security/
 ├── config/
 └── test/

notification-service/
 └── kafka-consumer/
```

--- 
## Cómo ejecutar el proyecto

### 1. Clonar repositorio

```bash
git clone https://github.com/gomomauricio/order-ms.git
cd order-ms
```

---

### 2. Levantar Kafka (Docker)

```bash
docker run -d -p 9092:9092 apache/kafka
```

---

### 3. Ejecutar servicios

```bash
# Order Service
cd order-service
./mvnw spring-boot:run

# Notification Service
cd ../notification-service
./mvnw spring-boot:run
```

---

## Endpoints principales

### Crear orden

```
POST /orders
```

Ejemplo:

```json
{
  "product": "Laptop",
  "quantity": 1,
  "price": 15000
}
```

---

## Swagger UI

Disponible en:

```
http://localhost:8081/swagger-ui.html
```

---

##  Seguridad

* Autenticación básica (configurable a JWT)
* Protección de endpoints
* Swagger expuesto públicamente

---

## Pruebas

Ejecutar tests:

```bash
./mvnw test
```

Incluye:

* Pruebas unitarias con Mockito
* Validación de servicios
* Verificación de interacción con Kafka

---

## Flujo de eventos

1. Se crea una orden vía API REST
2. Se guarda en base de datos (H2)
3. Se publica evento en Kafka (`order-created`)
4. Notification Service consume el evento
5. Se procesa (simulación de notificación)

---

##  Características destacadas

✔ Arquitectura basada en eventos
✔ Microservicios desacoplados
✔ Uso de Java 21 (records, mejoras modernas)
✔ Integración con Kafka
✔ Seguridad con Spring Security
✔ Documentación con OpenAPI
✔ Testing profesional con Mockito

---

## Mejoras futuras

* Implementar JWT real
* Agregar Docker Compose completo
* Integrar base de datos real (PostgreSQL)
* Agregar observabilidad (Zipkin, Prometheus)
* Implementar resiliencia (Resilience4j)

 
