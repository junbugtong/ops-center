# Migrations

Flyway applies the files in this folder in version order. Hibernate never
changes the schema (`ddl-auto: validate`).

- Name files `V<number>__<description>.sql`, for example `V1__create_incident.sql`.
- Never edit a migration that has been applied anywhere; add a new one.
- From Phase 8 on, follow expand/contract so the previous app version still
  works against the new schema.
