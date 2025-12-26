package com.example.demo.repository;

import com.example.demo.model.*;
import java.time.LocalDate;
import java.util.*;
public interface TeamCapacityConfigRepository {
    Optional<TeamCapacityConfig> findByTeamName(String team);
    TeamCapacityConfig save(TeamCapacityConfig c);
}
