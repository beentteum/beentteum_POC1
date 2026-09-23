package com.beentteum.crowdreportpoc.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrowdReportServiceTest {

    private CrowdReportRepository repository;
    private CrowdReportService service;

    @BeforeEach
    void setUp() {

        repository = new CrowdReportRepository();

        service = new CrowdReportService(repository);
    }

    @Test
    void 정상적으로_혼잡도를_등록할_수_있다() {

        String result =
                service.register(
                        1L,
                        1L,
                        "보통"
                );

        assertEquals(
                "혼잡도 제보 등록 완료",
                result
        );

        assertEquals(
                1,
                repository.count()
        );
    }

    @Test
    void 동일한_카페에_60분_이내_중복_제보할_수_없다() {

        service.register(
                1L,
                1L,
                "보통"
        );

        DuplicateReportException exception =
                assertThrows(
                        DuplicateReportException.class,
                        () -> service.register(
                                1L,
                                1L,
                                "혼잡"
                        )
                );

        assertEquals(
                "이미 최근에 혼잡도를 제보한 카페입니다.",
                exception.getMessage()
        );

        assertEquals(
                1,
                repository.count()
        );
    }

    @Test
    void 잘못된_혼잡도는_등록할_수_없다() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> service.register(
                                1L,
                                1L,
                                "매우혼잡"
                        )
                );

        assertEquals(
                "유효하지 않은 혼잡도입니다.",
                exception.getMessage()
        );
    }

    @Test
    void 사용자_ID가_없으면_등록할_수_없다() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.register(
                        null,
                        1L,
                        "여유"
                )
        );
    }
}