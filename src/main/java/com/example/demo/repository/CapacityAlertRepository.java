package com.example.demo.repository;

import com.example.demo.model.CapacityAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CapacityAlertRepository
        extends JpaRepository<CapacityAlert, Long> {

    List<CapacityAlert> findByTeamName(String teamName);

    List<CapacityAlert> findByAlertDate(LocalDate alertDate);
}
