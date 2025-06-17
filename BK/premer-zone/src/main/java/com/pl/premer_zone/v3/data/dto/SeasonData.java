package com.pl.premer_zone.v3.data.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeasonData {

	private List<PlayerGoalEntry> players_by_goals;
	private List<PlayerAssistEntry> players_by_assists;
	private List<LeagueTableEntry> league_table;
	private Map<String, Integer> team_goals;
	private BestPlayers best_players;
	private Map<String, List<PlayerStatEntry>> squad_stats_by_team;
}
