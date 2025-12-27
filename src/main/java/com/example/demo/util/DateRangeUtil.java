package com.example.demo.util;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class DateRangeUtil {
    public static List<LocalDate> daysBetween(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;
        while (!current.isAfter(end)) {
            dates.add(current);
            current = current.plusDays(1);
        }
        return dates;
    }
}