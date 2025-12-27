// File: src/main/java/com/example/demo/util/DateRangeUtil.java
package com.example.demo.util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class DateRangeUtil {
    public static List<LocalDate> daysBetween(LocalDate start, LocalDate end) {
        long numDays = end.toEpochDay() - start.toEpochDay() + 1;
        return LongStream.rangeClosed(0, numDays - 1)
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }
}
