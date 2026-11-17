package com.opscenter.incident.controller;

import com.opscenter.incident.Incident;

/** Converts the {@link Incident} entity into its API representation. */
final class IncidentResponseMapper {

    private IncidentResponseMapper() {}

    static IncidentResponse toResponse(Incident incident) {
        return new IncidentResponse(
                incident.getId(),
                incident.getTicketNumber(),
                incident.getTitle(),
                incident.getDescription(),
                incident.getStatus(),
                incident.getCreatedAt(),
                incident.getUpdatedAt());
    }
}
