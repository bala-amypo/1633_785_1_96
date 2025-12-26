package com.example.demo.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateRangeUtil {

    private DateRangeUtil() {
        // utility class – no object creation
    }

    /**
     * Returns a list of dates between startDate and endDate (inclusive)
     */
    public static List<LocalDate> getDatesBetween(
            LocalDate startDate,
            LocalDate endDate
    ) {
        List<LocalDate> dates = new ArrayList<>();

        if (startDate == null || endDate == null) {
            return dates;
        }

        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            dates.add(current);
            current = current.plusDays(1);
        }

        return dates;
    }
}
