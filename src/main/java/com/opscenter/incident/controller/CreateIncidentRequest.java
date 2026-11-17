package com.opscenter.incident.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** JSON body of {@code POST /incidents}. Limits match the incident table. */
public record CreateIncidentRequest(
        @NotBlank @Size(max = 200) String title,
        @NotBlank @Size(max = 10_000) String description) {}
