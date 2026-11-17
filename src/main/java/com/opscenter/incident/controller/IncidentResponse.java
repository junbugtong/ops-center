package com.opscenter.incident.controller;

import com.opscenter.incident.IncidentStatus;
import java.time.Instant;
import java.util.UUID;

/** What the API returns for one incident. Never expose the JPA entity itself. */
public record IncidentResponse(
        UUID id,
        String ticketNumber,
        String title,
        String description,
        IncidentStatus status,
        Instant createdAt,
        Instant updatedAt) {}
