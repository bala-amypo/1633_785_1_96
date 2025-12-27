// File: src/main/java/com/example/demo/util/DateRangeUtil.java
package com.example.demo.util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateRangeUtil {
    public static List<LocalDate> daysBetween(LocalDate start, LocalDate end) {
        return IntStream.rangeClosed(0, end.toEpochDay() - start.toEpochDay())
                .mapToObj(start::plusDays)
                .collect(Collectors.toList());
    }
}
