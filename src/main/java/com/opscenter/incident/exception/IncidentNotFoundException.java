package com.opscenter.incident.exception;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Thrown when no incident exists with the requested id. Spring answers it with 404. */
@ResponseStatus(HttpStatus.NOT_FOUND)
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
