package com.pl.premer_zone.v2.apifotbull;

import com.pl.premer_zone.v2.dto.PlayerDto;
import com.pl.premer_zone.v2.dto.TeamDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/football")
public class FootballApiController {

    private final FootballApiService footballApiService;

    public FootballApiController(FootballApiService footballApiService) {
        this.footballApiService = footballApiService;
    }

    @GetMapping("/teams")
    public ResponseEntity<List<TeamDto>> getTeams(@RequestParam int league, @RequestParam int season) {
        List<TeamDto> teams = footballApiService.getTeamsDto(league, season);
        return ResponseEntity.ok(teams);
    }
    @GetMapping("/players")
    public ResponseEntity<List<PlayerDto>> getPlayers(@RequestParam int teamId, @RequestParam int season){
        return ResponseEntity.ok(footballApiService.getPlayersByTeamAndSeason(teamId,season));
    }
}
