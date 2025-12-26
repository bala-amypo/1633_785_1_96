package com.example.demo.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateRangeUtil {

    public static List<LocalDate> daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End date before start date");
        }

        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;

        while (!current.isAfter(end)) {
            dates.add(current);
            current = current.plusDays(1);
        }
        return dates;
    }
}
