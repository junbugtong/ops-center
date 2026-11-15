# Next action

Read this at the start of each session and rewrite it at the end (plan, section 13.2).

## Now

1. Install JDK 21 and a container runtime, then run `./mvnw verify` and confirm
   `OpsCenterApplicationTests` passes against a Testcontainers PostgreSQL.
2. Push to a public GitHub repository and confirm the CI run is green.
3. Start the first slice with a failing test: `POST /incidents` returns 201
   with a `Location` header and a ticket number such as `INC-000001`.

## Phase 1 remaining (plan, section 7)

- [ ] `V1__create_incident.sql`: incident table, ticket-number sequence, status CHECK
- [ ] `POST /incidents` and `GET /incidents/{id}` with record DTOs and Jakarta Validation
- [ ] One problem-details shape for validation, not found, conflict and server errors
- [ ] Tests: each validation rule, 404 problem body, duplicate ticket number rejected by the constraint
- [ ] Migration test: Flyway applies cleanly to an empty database
- [ ] springdoc OpenAPI with examples
- [ ] First ArchUnit rule (modules reach each other only through public services)
- [ ] Static analysis in CI (Checkstyle or SpotBugs)
- [ ] Break a test on purpose once and watch CI fail
- [ ] README curl examples, module diagram, CI badge

## Parking lot

Ideas that are interesting but not this phase.
