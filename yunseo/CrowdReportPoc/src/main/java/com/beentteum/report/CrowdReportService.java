package com.beentteum.crowdreportpoc.report;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CrowdReportService {

    private final CrowdReportRepository repository;

    public CrowdReportService(CrowdReportRepository repository) {
        this.repository = repository;
    }

    public String register(
            Long userId,
            Long cafeId,
            String crowdLevelValue
    ) {

        // 1. 필수 입력값 검증
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException(
                    "사용자 ID가 필요합니다."
            );
        }

        if (cafeId == null || cafeId <= 0) {
            throw new IllegalArgumentException(
                    "카페 ID가 필요합니다."
            );
        }

        if (crowdLevelValue == null || crowdLevelValue.isBlank()) {
            throw new IllegalArgumentException(
                    "혼잡도 정보가 필요합니다."
            );
        }

        // 2. 혼잡도 값 검증
        CrowdLevel crowdLevel =
                CrowdLevel.from(crowdLevelValue);

        LocalDateTime now = LocalDateTime.now();

        // 3. 동일 사용자 + 동일 카페의 최근 제보 확인
        Optional<CrowdReport> latestReport =
                repository.findLatestByUserIdAndCafeId(
                        userId,
                        cafeId
                );

        // 4. 60분 이내 중복 제보 확인
        if (latestReport.isPresent()) {

            LocalDateTime duplicateLimit =
                    now.minusMinutes(60);

            LocalDateTime lastReportTime =
                    latestReport.get().getCreatedAt();

            if (!lastReportTime.isBefore(duplicateLimit)) {

                throw new DuplicateReportException(
                        "이미 최근에 혼잡도를 제보한 카페입니다."
                );
            }
        }

        // 5. 제보 저장
        repository.save(
                userId,
                cafeId,
                crowdLevel,
                now
        );

        return "혼잡도 제보 등록 완료";
    }
}