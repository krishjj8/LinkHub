# LinkHub - Cloud-Native DevSecOps Platform

[![Build and Deploy](https://github.com/krishjj8/LinkHub/actions/workflows/deploy.yaml/badge.svg)](https://github.com/krishjj8/LinkHub/actions/workflows/deploy.yaml)

LinkHub is a production-grade, distributed microservices platform engineered to solve the scalability challenges of high-traffic link sharing. Beyond standard CRUD operations, this project serves as a rigorous implementation of modern **Platform Engineering** principles, demonstrating how to architect, secure, and automate a cloud-native system from the ground up.

It transitions from a traditional manual deployment model to a fully automated **GitOps** workflow, leveraging Kubernetes and Infrastructure as Code to ensure reliability and reproducibility.

---

## System Architecture & Engineering Decisions

The platform allows users to create public profiles and manage collections of links. To handle potential high-read traffic (e.g., a viral profile), the architecture has been evolved from a simple monolithic app into a resilient distributed system.

### 1. Distributed Caching Strategy (Redis)
To protect the primary database from read-heavy load spikes, I implemented a **Cache-Aside** pattern using Redis.
* **The Logic:** Incoming read requests first check the in-memory Redis cache. If the data exists (Cache Hit), it is returned instantly with sub-millisecond latency, bypassing the database entirely.
* **The Impact:** Drastically reduces latency for frequently accessed user profiles and offloads significant pressure from the PostgreSQL database.

### 2. Zero-Touch Deployment (GitOps)
I moved away from manual `kubectl` commands to a mature, pull-based deployment model using **ArgoCD**.
* **Continuous Integration (CI):** A GitHub Actions pipeline automatically builds the Java application, containerizes it, pushes the artifact to a private **AWS ECR** registry, and commits the new image tag back to the repository.
* **Continuous Delivery (CD):** ArgoCD monitors the repository for changes and automatically syncs the live Kubernetes cluster to the desired state. This ensures that the code in Git is always the single source of truth for the production environment.

### 3. Ephemeral Infrastructure (Terraform)
The entire cloud environment is defined as code, allowing for the rapid provisioning and destruction of resources to optimize cloud costs (FinOps).
* **Networking:** Custom VPC with public/private subnet isolation to secure the database layer.
* **Compute:** Provisioned EC2 instances running K3s (Lightweight Kubernetes).
* **Storage:** Managed AWS RDS (PostgreSQL) for persistent data storage.

---

## Technology Stack

| Domain | Technology Choice |
| :--- | :--- |
| **Core Backend** | Java 17, Spring Boot 3, Spring Data JPA |
| **Data & Caching** | PostgreSQL (AWS RDS), Redis (Cluster Cache) |
| **Orchestration** | Kubernetes (K3s), Traefik Ingress |
| **GitOps & CI/CD** | ArgoCD, GitHub Actions, AWS ECR |
| **Infrastructure** | Terraform (IaC), AWS VPC, EC2 |
| **Development** | Docker, Docker Compose, Maven |

---

## Project Roadmap & Status

This project is actively being engineered with a focus on performance and automation.

- [x] **Cloud Infrastructure:** Architected a secure, multi-tier AWS network using Terraform.
- [x] **Kubernetes Migration:** Migrated workloads from local Docker Compose to a live K3s cluster on AWS.
- [x] **GitOps Implementation:** Established a complete CI/CD pipeline bridging GitHub Actions and ArgoCD.
- [x] **Performance Optimization:** Integrated Redis to implement distributed caching for high-speed reads.
- [ ] **Core Logic Upgrade:** Implementing Base62 encoding for optimized unique ID generation and URL shortening.
- [ ] **Observability:** Integrating Prometheus and Grafana for real-time metric visualization.

---

## How to Run

### Local Development (Docker)
You can spin up the entire stack locally without touching the cloud.

1.  Clone the repository:
    ```bash
    git clone [https://github.com/krishjj8/LinkHub.git](https://github.com/krishjj8/LinkHub.git)
    ```
2.  Start the environment (Apps + DB + Redis):
    ```bash
    docker compose up --build
    ```
3.  Access the API at `http://localhost:8080`.

### Cloud Deployment (Terraform)
To provision the production environment on AWS:

1.  Navigate to the infrastructure directory:
    ```bash
    cd infrastructure
    ```
2.  Initialize and apply the Terraform configuration:
    ```bash
    terraform init
    terraform apply
    ```
3.  Once provisioned, the system will output the cluster IP and database endpoints required for the Kubernetes manifest configuration.
