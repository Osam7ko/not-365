package com.pl.premer_zone.v2.apifotbull;

import com.pl.premer_zone.v2.dto.TeamSummaryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/football/teams")
public class TeamStatsController {

    private final TeamStatsService teamStatsService;

    public TeamStatsController(TeamStatsService teamStatsService) {
        this.teamStatsService = teamStatsService;
    }

    @GetMapping("/stats")
    public ResponseEntity<List<TeamSummaryDto>> getTeamStats(@RequestParam int league, @RequestParam int season) {
        return ResponseEntity.ok(teamStatsService.getTeamSummaries(league, season));
    }
}