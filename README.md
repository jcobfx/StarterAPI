# Demo Java REST API

A simple Spring Boot starter project with JWT authentication.

## Table of Contents
- [Demo Java REST API](#demo-java-rest-api)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Tech Stack](#tech-stack)
  - [Getting Started](#getting-started)
  - [Screenshots](#screenshots)
  - [Author](#author)

---

## Features

- User registration and login
- Create, edit, and delete tasks
- Deadlines
- Secure REST API with JWT authentication

---

## Tech Stack

- Java 21
- Spring Boot (Spring Security, Spring Data JPA, REST)
- PostgreSQL
- JWT
- Lombok
- Swagger UI

---

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/jcobfx/StarterAPI.git
    cd StarterAPI
    ```
2. Configure your environment variables by creating `.env` file in the root directory of a cloned project.
    ```dotenv
    DB_URL=jdbc:postgresql://localhost:5432/your_database_name
    DB_USERNAME=your_database_username
    DB_PASSWORD=your_database_password
    JWT_SECRET=your_jwt_secret_key
    JWT_EXPIRATION=3600000 # 1 hour in milliseconds
    ```
   Make sure to replace `your_database_name`, `your_database_username`, `your_database_password`, and `your_jwt_secret_key` with your actual values.
3. Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```
4. Access the API with Swagger UI at `http://localhost:8080/swagger-ui.html`.

---

## Screenshots

Api Documentation available at `http://localhost:8080/swagger-ui.html`

### Swagger UI - Endpoints

![](/assets/swagger-ui-endpoints.png "Swagger UI Endpoints")

### Swagger UI - Schemas

![](/assets/swagger-ui-schemas.png "Swagger UI Schemas")

---

## Author

### Jakub Foks [![GitHub followers](https://img.shields.io/github/followers/jcobfx?style=social)]()

Computer Science student & backend developer passionate about Java and clean code.

---

[![GitHub](https://img.shields.io/badge/GitHub-Profile-black)](https://www.github.com/jcobfx)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue)](https://www.linkedin.com/in/jakub-foks-a15298270/)
[![Email](https://img.shields.io/badge/Email-Contact-red)](mailto:jakub@foks.com.pl)
