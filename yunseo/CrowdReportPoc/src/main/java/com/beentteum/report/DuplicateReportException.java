package com.beentteum.crowdreportpoc.report;

public class DuplicateReportException extends RuntimeException {

    public DuplicateReportException(String message) {
        super(message);
    }
}