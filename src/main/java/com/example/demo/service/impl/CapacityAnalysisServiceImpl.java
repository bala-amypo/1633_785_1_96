package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CapacityAnalysisService;

import java.time.LocalDate;
import java.util.*;

public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {

    private final TeamCapacityConfigRepository configRepo;
    private final LeaveRequestRepository leaveRepo;
    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(TeamCapacityConfigRepository c,
                                       EmployeeProfileRepository e,
                                       LeaveRequestRepository l,
                                       CapacityAlertRepository a) {
        this.configRepo = c;
        this.leaveRepo = l;
        this.alertRepo = a;
    }

    public CapacityAnalysisResultDto analyzeTeamCapacity(String team, LocalDate s, LocalDate e) {
        if (s.isAfter(e)) throw new BadRequestException("Start date invalid");

        TeamCapacityConfig cfg = configRepo.findByTeamName(team)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));

        if (cfg.getTotalHeadcount() <= 0)
            throw new BadRequestException("Invalid total headcount");

        int leaves = leaveRepo.findApprovedOverlappingForTeam(team, s, e).size();
        int available = cfg.getTotalHeadcount() - leaves;
        int percent = (available * 100) / cfg.getTotalHeadcount();

        Map<LocalDate, Integer> map = new HashMap<>();
        map.put(s, percent);

        CapacityAnalysisResultDto res = new CapacityAnalysisResultDto();
        res.setCapacityByDate(map);
        res.setRisky(percent < cfg.getMinCapacityPercent());

        if (res.isRisky()) {
            alertRepo.save(new CapacityAlert(team, s, "HIGH", "Low capacity"));
        }
        return res;
    }
}
