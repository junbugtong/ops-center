package com.opscenter.incident.exception;

import java.util.UUID;

public class IncidentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final UUID incidentId;

    public IncidentNotFoundException(UUID incidentId) {
        super("Incident %s was not found".formatted(incidentId));
        this.incidentId = incidentId;
    }

    public UUID getIncidentId() {
        return incidentId;
    }
}
