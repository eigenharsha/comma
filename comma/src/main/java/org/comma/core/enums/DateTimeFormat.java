package org.comma.core.enums;

import org.comma.config.ApplicatonConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class DateTimeFormat {
    @Autowired
    ApplicatonConfig config;

    private final String DD_MM_YYYY = "dd-mm-yyyy";
    private final String DD_MMM_YYYY= "dd-mmm-yyyy";
    private final String DD_MM_YY= "dd-mm-yy";
    private final String MM_DD_YYYY= "dd-mm-yyyy";
    private final String MMM_DD_YYYY= "dd-mmm-yyyy";
    private final String MM_DD_YY= "dd-mm-yy";
    private final String YYYY_MM_DD= "dd-mm-yyyy";
    private final String YYYY_MMM_DD= "dd-mmm-yyyy";
    private final String YY_MM_DD= "dd-mm-yy";
    private final String DD_MM_YYYY_hh_mm_ss= "dd-mm-yyyy HH:mm:ss";

    private String format;

    public String pattern() {
        return format;
    }
}
