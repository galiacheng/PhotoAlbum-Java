# Modernization Plan: Photo Album Java to Azure

**Project**: Photo Album

---

## Technical Framework

- **Language**: Java 25
- **Framework**: Spring Boot 4.0.6
- **Build Tool**: Maven
- **Database**: Oracle Database (local/docker), H2 (tests)
- **Key Dependencies**: Spring Web, Thymeleaf, Spring Data JPA, Oracle JDBC

---

## Overview

> This migration moves the Photo Album application to Azure. The application
> currently runs as a containerized Spring Boot service backed by Oracle for
> local and Docker-based environments. The new architecture will:
>
> - move the operational database workload to an Azure-managed PostgreSQL
>   service to reduce dependency on local Oracle infrastructure
> - deploy the application on Azure Kubernetes Service using the repository's
>   existing Azure-oriented setup and container workflow
> - validate dependency security before deployment so the Azure target starts
>   from a remediated baseline
>
> The migration follows a phased approach that first updates the data platform,
> then performs required security remediation, and finally deploys the
> application to Azure.

---

## Migration Impact Summary

| Application | Original Service | New Azure Service | Authentication | Comments |
|-------------|------------------|-------------------|----------------|----------|
| Photo Album | Oracle Database  | Azure Database    | App creds,     | Replace  |
|             |                  | for PostgreSQL    | later MI-ready | Oracle   |
| Photo Album | Local container  | Azure Kubernetes  | Azure deploy   | Use repo |
|             | hosting          | Service           | credentials    | AKS flow |

---

## Planned Workstreams

- Migrate the application's Oracle database dependency to Azure Database for
  PostgreSQL while preserving existing photo-management behavior.
- Run dependency security review and remediate known CVEs before Azure rollout.
- Deploy the containerized application to Azure Kubernetes Service using the
  repository-aligned Azure setup flow already present in the project.

---

## Open Questions & Questionnaire

- [x] Q: Should the plan include environment/infrastructure provisioning? → A:
  No — use the repository's existing Azure setup and deployment assets rather
  than adding new infrastructure-generation scope.
- [x] Q: Should the plan include integration testing to verify migrated
  services? → A: No — the request did not explicitly ask for a separate
  integration-test task, so the plan stays focused on migration and deployment.
- [x] Q: Should the plan include a security scan and CVE remediation task? →
  A: Yes — include the default security remediation task required by the
  modernization planning templates.
- [x] Q: Which Azure deployment target should the plan use? → A: Azure
  Kubernetes Service, inferred from the repository's existing Azure setup
  script that provisions AKS, ACR, and Azure Database for PostgreSQL.
- [x] Q: Should the plan include a separate containerization task? → A: No —
  the deployment task covers container build and deployment work for the AKS
  target.
