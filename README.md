# LinkHub - A Cloud-Native Microservices Platform

A multi-service, link-sharing platform (similar to Linktree) built from the ground up using modern, professional backend and DevOps practices. This project is currently in development

## Current Features (As of Week 2)
- Full CRUD (Create, Read, Update, Delete) functionality for User management via a REST API.
- Relational database schema with a one-to-many relationship between Users and Links.
- A complete, containerized development environment managed by Docker Compose.
- A foundational suite of unit tests for the service layer.

## Tech Stack
- **Backend:** Java 17, Spring Boot 3
- **Database:** PostgreSQL
- **Testing:** JUnit 5, Mockito
- **Containerization:** Docker, Docker Compose

## Upcoming Features
- [ ] CI/CD Pipeline with GitHub Actions
- [ ] Infrastructure as Code (IaC) with Terraform
- [ ] Deployment to AWS using Kubernetes (EKS)
- [ ] Observability with Prometheus & Grafana

## How to Run Locally

1.  Clone the repository: `git clone https://github.com/krishjj8/LinkHub.git`
2.  Navigate into the project directory: `cd LinkHub`
3.  Ensure you have Docker and Docker Compose installed and running.
4.  Run the application using Docker Compose:
    ```bash
    docker compose up --build
    ```
5.  The `link-service` API will be available at `http://localhost:8080`.
