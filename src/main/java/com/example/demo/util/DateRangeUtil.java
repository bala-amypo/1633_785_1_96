package com.example.demo.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateRangeUtil {

    private DateRangeUtil() {}

    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(start, end);
    }
}
