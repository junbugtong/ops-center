package com.opscenter.incident.repository;

import com.opscenter.incident.Incident;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, UUID> {

    @Query(value = "select nextval('incident_ticket_seq')", nativeQuery = true)
    long nextTicketSequence();
}
