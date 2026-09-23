package com.beentteum.crowdreportpoc.report;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class CrowdReportController {

    private final CrowdReportService service;

    public CrowdReportController(CrowdReportService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> register(
            @RequestBody CrowdReportRequest request
    ) {

        String message = service.register(
                request.userId(),
                request.cafeId(),
                request.crowdLevel()
        );

        return ResponseEntity.ok(
                Map.of("message", message)
        );
    }
}