package com.beentteum.crowdreportpoc.report;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CrowdReportRepository {

    private final List<CrowdReport> reports = new ArrayList<>();

    private long sequence = 1L;

    public synchronized CrowdReport save(
            Long userId,
            Long cafeId,
            CrowdLevel crowdLevel,
            LocalDateTime createdAt
    ) {

        CrowdReport report = new CrowdReport(
                sequence++,
                userId,
                cafeId,
                crowdLevel,
                createdAt
        );

        reports.add(report);

        return report;
    }

    public Optional<CrowdReport> findLatestByUserIdAndCafeId(
            Long userId,
            Long cafeId
    ) {

        for (int i = reports.size() - 1; i >= 0; i--) {

            CrowdReport report = reports.get(i);

            if (report.getUserId().equals(userId)
                    && report.getCafeId().equals(cafeId)) {

                return Optional.of(report);
            }
        }

        return Optional.empty();
    }

    public int count() {
        return reports.size();
    }

    public void clear() {
        reports.clear();
        sequence = 1L;
    }
}