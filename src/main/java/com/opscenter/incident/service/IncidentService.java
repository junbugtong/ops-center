package com.opscenter.incident.service;

import com.opscenter.incident.Incident;
import com.opscenter.incident.repository.IncidentRepository;
import java.time.Clock;
import java.time.Instant;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IncidentService {

    private final Clock clock;

    private final IncidentRepository incidentRepository;

    public IncidentService(Clock clock, IncidentRepository incidentRepository) {
        this.clock = clock;
        this.incidentRepository = incidentRepository;
    }

    @Transactional
    public Incident createIncident(String title, String description) {
        String ticketNumber = getFormattedTicketNumber();
        Instant now = clock.instant();

        return incidentRepository.save(Incident.create(ticketNumber, title, description, now));
    }

    private @NonNull String getFormattedTicketNumber() {
        return "INC-%06d".formatted(incidentRepository.nextTicketSequence());
    }
}
