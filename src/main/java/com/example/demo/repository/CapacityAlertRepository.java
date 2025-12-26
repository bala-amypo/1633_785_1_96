package com.example.demo.repository;

import com.example.demo.model.*;
import java.time.LocalDate;
import java.util.*;
public interface CapacityAlertRepository {
    CapacityAlert save(CapacityAlert a);
    List<CapacityAlert> findByTeamNameAndDateBetween(String team, LocalDate s, LocalDate e);
}
