# PayGuard — Duplicate Payment Prevention API

PayGuard is a Spring Boot REST API designed to prevent duplicate payment processing using **idempotency keys**. It helps ensure that repeated requests with the same key do not create multiple payment records.

## 🚀 Features

- RESTful API for payment creation.
- Idempotency key support to prevent duplicate payment requests.
- Payment status management.
- MySQL database integration using Spring Data JPA.
- Structured payment and idempotency key entities.
- Automatic creation timestamps.
- Request handling using Spring Boot.

## 🛠️ Tech Stack

- **Language:** Java
- **Backend:** Spring Boot
- **Database:** MySQL
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **API Testing:** Postman
- **Version Control:** Git and GitHub

## 📂 Project Structure

```text
PayGuard/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/Shravan/PayGuard/
│       │       ├── contoller/
│       │       ├── modal/
│       │       └── repo/
│       └── resources/
│           └── application.properties
├── pom.xml
└── README.md
```

## ⚙️ Prerequisites

Before running the project, install:

- Java JDK
- Maven
- MySQL Server
- An IDE such as Eclipse or IntelliJ IDEA
- Postman (optional, for API testing)

## 🔧 Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/Shravank9/PayGuard.git
cd PayGuard
```

### 2. Configure the database

Create a MySQL database:

```sql
CREATE DATABASE payguard;
```

Configure your database connection in `src/main/resources/application.properties`.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/payguard
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Set `DB_USERNAME` and `DB_PASSWORD` to your MySQL credentials before running the application.

**Security:** Never commit real database passwords, API keys, or other secrets to GitHub.

### 3. Run the application

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively, run the main Spring Boot application class from your IDE.

## 🔌 API Endpoint

### Create a Payment

**Endpoint:**

```http
POST /api/payments
```

**Request Header:**

```http
Idempotency-Key: unique-payment-key-001
```

**Request Parameters:**

| Parameter | Description |
|---|---|
| `amount` | Payment amount |
| `currency` | Payment currency |

Example request:

```http
POST http://localhost:8080/api/payments?amount=500&currency=INR
Idempotency-Key: unique-payment-key-001
```

Send the request using Postman with the `Idempotency-Key` header and the required parameters.

Use the same idempotency key when retrying the same payment request.

> Note: The API contract and response behavior depend on the current controller and service implementation. Ensure duplicate requests return the intended result before describing the behavior as fully implemented.

## 📊 Payment Statuses

PayGuard defines the following payment statuses:

- `PENDING` — Payment is awaiting processing.
- `SUCCESS` — Payment completed successfully.
- `FAILED` — Payment processing failed.

## 🎯 Project Objective

The goal of PayGuard is to demonstrate how idempotency can help prevent duplicate payment records when clients retry requests due to network failures, timeouts, or accidental repeated submissions.

## 🔮 Future Enhancements

- Implement transactional payment processing.
- Add comprehensive duplicate-request handling.
- Introduce input validation and centralized exception handling.
- Add authentication and authorization.
- Write unit and integration tests.
- Add API documentation using Swagger/OpenAPI.
- Implement payment gateway integration.

## 👨‍💻 Author

**Shravan K**

GitHub: [Shravank9](https://github.com/Shravank9)

---

⭐ If you find this project useful, consider giving the repository a star.
