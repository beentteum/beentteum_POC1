package com.beentteum.crowdreportpoc.report;

import java.time.LocalDateTime;

public class CrowdReport {

    private final Long id;
    private final Long userId;
    private final Long cafeId;
    private final CrowdLevel crowdLevel;
    private final LocalDateTime createdAt;

    public CrowdReport(
            Long id,
            Long userId,
            Long cafeId,
            CrowdLevel crowdLevel,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.userId = userId;
        this.cafeId = cafeId;
        this.crowdLevel = crowdLevel;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCafeId() {
        return cafeId;
    }

    public CrowdLevel getCrowdLevel() {
        return crowdLevel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}