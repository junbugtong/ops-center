package com.opscenter.incident;

/**
 * Lifecycle states of an incident. Keep in step with the
 * {@code ck_incident_status} CHECK constraint in the database.
 */
public enum IncidentStatus {
    NEW,
    TRIAGED,
    ASSIGNED,
    IN_PROGRESS,
    RESOLVED,
    CLOSED,
    REOPENED,
    CANCELLED
}
