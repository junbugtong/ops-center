package com.opscenter.incident.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Turns exceptions into RFC 9457 problem-details responses ({@code application/problem+json}).
 *
 * <p>Extending {@link ResponseEntityExceptionHandler} makes Spring's own errors use the same shape:
 * validation failures, unreadable JSON and path values of the wrong type (such as a malformed
 * UUID) all become 400 problem details without extra code here.
 */
@RestControllerAdvice
public class OpsCenterExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IncidentNotFoundException.class)
    public ProblemDetail handleIncidentNotFound(IncidentNotFoundException exception) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problem.setTitle("Incident not found");
        problem.setProperty("incidentId", exception.getIncidentId());
        return problem;
    }
}
