package com.pl.premer_zone.v3.playerST;

import java.util.List;

public interface PlayerStatsService {

	PlayerStatsDto createPlayerStats(PlayerStatsDto dto);

	List<PlayerStatsDto> getAllStats();

	PlayerStatsDto getById(Long id);

	List<PlayerStatsDto> getStatsBySeason(Long seasonId);

	List<PlayerStatsDto> getStatsByPlayer(Long playerId);

	PlayerStatsDto updatePlayerStats(Long id, PlayerStatsDto dto);

	PlayerStatsDto getLatestStatsForPlayer(Long playerId);

	List<PlayerStatsDto> getTopScorers(int limit);

	void deletePlayerStats(Long id);

	List<PlayerStatsDto> getTopScorersBySeason(Long seasonId, int limit);

	List<PlayerStatsDto> getTopAssistsBySeason(Long seasonId, int limit);

}
