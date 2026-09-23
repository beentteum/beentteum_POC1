package com.beentteum.crowdreportpoc.report;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadRequest(
            IllegalArgumentException exception
    ) {

        return Map.of(
                "message",
                exception.getMessage()
        );
    }

    @ExceptionHandler(DuplicateReportException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleDuplicate(
            DuplicateReportException exception
    ) {

        return Map.of(
                "message",
                exception.getMessage()
        );
    }
}