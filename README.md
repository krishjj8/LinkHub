# LinkHub - A Cloud-Native Microservices Platform

[![Link Service CI/CD Pipeline](https://github.com/krishjj8/LinkHub/actions/workflows/ci-pipeline.yml/badge.svg)](https://github.com/krishjj8/LinkHub/actions/workflows/ci-pipeline.yml)

LinkHub is a multi-service, link-sharing platform built from the ground up to demonstrate a full software development lifecycle using modern backend and DevOps practices. The project serves as a practical implementation of a cloud-native architecture, from local containerized development to automated continuous integration and future cloud deployment.

---

## Core Architecture and Features

The platform is designed as a distributed system with a professional 3-tier architecture, emphasizing a clean separation of concerns.

* **Full CRUD API:** The system exposes a complete REST API with full Create, Read, Update, and Delete functionality for both `User` and `Link` entities, including the management of their relational mappings.

* **Microservice Design:** The application is broken down into distinct services to handle different business domains:
    * `link-service`: The core backend API responsible for all user and link management.
    * `analytics-service`: A secondary service designed for background tasks, such as tracking link clicks.

* **Containerized Environment:** The entire application stack, including the backend services and the PostgreSQL database, is fully containerized using **Docker**. The local development environment is managed with **Docker Compose**, allowing the entire platform to be launched with a single command.

* **Automated CI/CD Pipeline:** A complete Continuous Integration and Delivery pipeline has been engineered using **GitHub Actions**. On every push to the `main` branch, this workflow automatically:
    1.  Executes the full suite of **JUnit/Mockito** unit tests to validate code integrity.
    2.  Builds fresh Docker images for the services.
    3.  Pushes the tagged images securely to a private **AWS Elastic Container Registry (ECR)** repository.

---

## Technology Stack

| Category | Technologies |
| :--- | :--- |
| **Backend & Databases** | Java 17, Spring Boot 3, Spring Data JPA, PostgreSQL |
| **Containerization** | Docker, Docker Compose |
| **Automation & CI/CD** | GitHub Actions, Git, Maven |
| **Infrastructure as Code** | Terraform |
| **Testing** | JUnit 5, Mockito |
| **Cloud** | AWS (ECR, VPC) |

---

## Project Roadmap

The project is currently in the cloud infrastructure phase, with plans to complete a full deployment to AWS.

-   [x] **Infrastructure as Code (IaC):** Provisioning all cloud resources, including a secure multi-layered network (VPC) and firewalls (Security Groups), on AWS using **Terraform**.
-   [ ] **Kubernetes Deployment:** Deploy the containerized microservices to a **Kubernetes** cluster (k3s on EC2) running in the provisioned AWS environment.
-   [ ] **GitOps Automation:** Implement a fully automated, pull-based deployment workflow using **ArgoCD** for managing the live application state.
-   [ ] **Observability:** Integrate a monitoring stack with **Prometheus** and **Grafana** to provide dashboards and alerts for application performance and system health.

---

## How to Run Locally

1.  Clone the repository:
    ```bash
    git clone [https://github.com/krishjj8/LinkHub.git](https://github.com/krishjj8/LinkHub.git)
    ```
2.  Navigate into the project directory:
    ```bash
    cd LinkHub
    ```
3.  Ensure you have Docker and Docker Compose installed and running.
4.  Build and run the application using Docker Compose. The `--build` flag will build the necessary images for the first time.
    ```bash
    docker compose up --build
    ```
5.  The `link-service` API will then be available at `http://localhost:8080`.
