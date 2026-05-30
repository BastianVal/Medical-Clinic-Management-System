# Clinic Appointments API

A robust, enterprise-grade RESTful API built to manage clinical appointments, medical staff, and patient records. This backend system is designed with a strong focus on security, scalability, and seamless cloud deployment.

## 🚀 Overview

This application serves as the core backend for a clinic management system. It handles user authentication, role-based access control, and the core business logic for scheduling and managing appointments. The architecture follows industry best practices, separating development, testing, and production environments, and relies on an external cloud database (Supabase) to ensure data integrity and high availability.

## ✨ Key Features

* **Secure Authentication:** Implementation of Spring Security with custom JWT (JSON Web Tokens) filters for stateless authentication and authorization.
* **Role-Based Access Control:** Differentiated access levels for Admins, Coordinators, Doctors, and Patients.
* **Smart Data Management:** Implementation of "Soft Deletes" (logical deletion) for user management, preventing active sessions for deactivated accounts.
* **Automated Seeding:** Built-in data seeders to initialize administrative accounts safely upon deployment.
* **Testing Environment:** Configured with an isolated H2 in-memory database to execute integration tests without compromising production data.
* **Containerized Architecture:** Fully dockerized using multi-environment `docker-compose` setups for seamless local development and production deployment.

## 🛠️ Tech Stack

* **Framework:** Java 21 / Spring Boot 3 (Spring Web, Spring Data JPA)
* **Security:** Spring Security & `jjwt` (0.11.5)
* **Database:** PostgreSQL (Hosted on AWS via Supabase)
* **Testing:** JUnit 5, Mockito, H2 Database
* **Containerization:** Docker & Docker Compose
* **Boilerplate Reduction:** Lombok

## 🏗️ Architecture & Deployment

The system is designed for modern cloud infrastructure (Polyrepo approach). The API runs inside a lightweight Alpine Linux Docker container, exposing port `8080`.

It is optimized for **AWS EC2** deployments, relying on environment variables (`.env`) injected at runtime to keep sensitive credentials (like the `JWT_SECRET` and database passwords) completely secure and out of the source code.

## 🚦 Getting Started

### Prerequisites
* Docker & Docker Compose
* Java 21 (If running locally without Docker)

### Running with Docker (Recommended)
1. Clone the repository.
2. Create an `.env` file in the root directory based on `.env.example`.
3. Build and start the container:
   ```bash
   docker-compose up --build -d
