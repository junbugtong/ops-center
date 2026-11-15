# OpsCenter Lite

An incident-management backend built as a Spring Boot modular monolith. The
goal is not the features but proving they stay correct under retries,
concurrency, partial failure, restarts and horizontal scaling.

Status: Phase 1 (foundation) in progress. See [docs/NEXT_ACTION.md](docs/NEXT_ACTION.md).

## Prerequisites

- JDK 21
- Docker Engine with Compose (or a compatible runtime such as Podman or Rancher Desktop)

Maven is not required; the wrapper (`mvnw`) downloads it.

## Start

```bash
docker compose up -d
./mvnw spring-boot:run
```

The API listens on http://localhost:8080.

| Check | URL |
|---|---|
| Liveness (is the process alive?) | http://localhost:8080/actuator/health/liveness |
| Readiness (can it serve traffic? includes the database) | http://localhost:8080/actuator/health/readiness |

To run everything in containers instead:

```bash
docker compose --profile app up --build
```

## Test

```bash
./mvnw verify
```

Integration tests start their own PostgreSQL through Testcontainers, so Docker
must be running but Compose does not need to be up.

## Reset

Deletes the local database volume:

```bash
docker compose down -v
```

## Configuration

| Variable | Default (local only) | Purpose |
|---|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/opscenter` | JDBC URL |
| `DB_USERNAME` | `opscenter` | Database user |
| `DB_PASSWORD` | `opscenter` | Database password |

The defaults exist only so a clean clone starts with one command. No real
secret belongs in this repository.

## Conventions

- **Schema:** Flyway owns it (`src/main/resources/db/migration`); Hibernate only validates.
- **Time:** inject `java.time.Clock`; never call `Instant.now()` directly. Store UTC.
- **Logs:** JSON on the console. Every request carries an `X-Correlation-Id`
  header (read or generated) that appears in each log line as `correlationId`.
- **Structure:** package by feature under `com.opscenter` (`incident`, `assignment`,
  `sla`, ...). `shared` stays tiny: identifiers, clock, error model, correlation context.
- **Decisions:** one-page ADRs in `docs/adr`.

## Troubleshooting

- **Port 5432 already in use:** another PostgreSQL is running. Stop it or change the published port in `compose.yaml` and `DB_URL`.
- **Tests fail with "Could not find a valid Docker environment":** the container runtime is not running.
- **Readiness is DOWN:** the database is unreachable; check `docker compose ps`.
- **Plain-text logs while developing:** `./mvnw spring-boot:run -Dspring-boot.run.arguments=--logging.structured.format.console=`

## Licences

Third-party tools used by this project keep their own licences; Grafana and k6
(later phases) are AGPL v3 and are run unmodified.
