package com.beentteum.crowdreportpoc.report;

public record CrowdReportRequest(
        Long userId,
        Long cafeId,
        String crowdLevel
) {
}