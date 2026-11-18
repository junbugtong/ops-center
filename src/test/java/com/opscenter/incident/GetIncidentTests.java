package com.opscenter.incident;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import com.opscenter.TestcontainersConfiguration;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class GetIncidentTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAndGetIncidentShouldReturnIncidentTest() throws Exception {
        String body = """
            {
              "title": "Payments API returning 500s",
              "description": "Checkout requests fail with HTTP 500 since 09:40 UTC."
            }
            """;

        // Arrange: create an incident through the API, as a client would.
        MvcResult created = mockMvc.perform(post("/incidents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();

        String location = created.getResponse().getHeader(HttpHeaders.LOCATION);
        String ticketNumber = JsonPath.read(created.getResponse().getContentAsString(), "$.ticketNumber");

        // Act and assert: follow the Location header and get the same incident back.
        mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketNumber").value(ticketNumber))
                .andExpect(jsonPath("$.title").value("Payments API returning 500s"))
                .andExpect(jsonPath("$.description").value("Checkout requests fail with HTTP 500 since 09:40 UTC."))
                .andExpect(jsonPath("$.status").value("NEW"));
    }

    @Test
    void getIncidentShouldReturnNotFoundWhenRecordIsNotExistingTest() throws Exception {
        UUID unknownId = UUID.randomUUID();

        mockMvc.perform(get("/incidents/{id}", unknownId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title").value("Incident not found"))
                .andExpect(jsonPath("$.detail").value("Incident %s was not found".formatted(unknownId)))
                .andExpect(jsonPath("$.incidentId").value(unknownId.toString()))
                .andExpect(jsonPath("$.instance").value("/incidents/" + unknownId));
    }

    @Test
    void getIncidentShouldReturnBadRequestForInvalidUuidTest() throws Exception {
        mockMvc.perform(get("/incidents/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(400));
    }
}
