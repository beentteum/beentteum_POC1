package com.beentteum.crowdreportpoc.report;
public enum CrowdLevel {

    FREE("여유"),
    NORMAL("보통"),
    CROWDED("혼잡");

    private final String label;

    CrowdLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static CrowdLevel from(String value) {

        for (CrowdLevel crowdLevel : values()) {
            if (crowdLevel.label.equals(value)) {
                return crowdLevel;
            }
        }

        throw new IllegalArgumentException("유효하지 않은 혼잡도입니다.");
    }
}