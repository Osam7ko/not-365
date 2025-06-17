package com.pl.premer_zone.v3.playerST;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pl.premer_zone.v3.player.Player;
import com.pl.premer_zone.v3.player.PlayerRepository;
import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.season.SeasonRepository;
import com.pl.premer_zone.v3.team.Team;
import com.pl.premer_zone.v3.team.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerStatsServiceImpl implements PlayerStatsService {

	private final PlayerStatsRepository psRepository;
	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;
	private final SeasonRepository seasonRepository;

	@Override
	public PlayerStatsDto createPlayerStats(PlayerStatsDto dto) {
		Player player = playerRepository.findById(dto.getPlayerId())
				.orElseThrow(() -> new RuntimeException("Player not found"));
		Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));
		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));

		PlayerStats stats = PlayerStatsMapper.toEntity(dto, player, team, season);
		stats = psRepository.save(stats);

		return PlayerStatsMapper.toDto(stats);
	}

	@Override
	public List<PlayerStatsDto> getAllStats() {
		return psRepository.findAll().stream().map(PlayerStatsMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public PlayerStatsDto getById(Long id) {
		PlayerStats stats = psRepository.findById(id).orElseThrow(() -> new RuntimeException("PlayerStats not found"));
		return PlayerStatsMapper.toDto(stats);
	}

	@Override
	public List<PlayerStatsDto> getStatsBySeason(Long seasonId) {
		Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new RuntimeException("Season not found"));

		return psRepository.findAll().stream().filter(s -> s.getSeason().equals(season)).map(PlayerStatsMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<PlayerStatsDto> getStatsByPlayer(Long playerId) {
		Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
		return psRepository.findAll().stream().filter(s -> s.getPlayer().equals(player)).map(PlayerStatsMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public PlayerStatsDto updatePlayerStats(Long id, PlayerStatsDto dto) {
		PlayerStats stats = psRepository.findById(id).orElseThrow(() -> new RuntimeException("PlayerStats not found"));

		Player player = playerRepository.findById(dto.getPlayerId())
				.orElseThrow(() -> new RuntimeException("Player not found"));
		Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));
		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));

		stats.setPlayer(player);
		stats.setTeam(team);
		stats.setSeason(season);
		stats.setGoals(dto.getGoals());
		stats.setAssists(dto.getAssists());

		return PlayerStatsMapper.toDto(psRepository.save(stats));
	}

	@Override
	public PlayerStatsDto getLatestStatsForPlayer(Long playerId) {
		Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));

		return psRepository.findTopByPlayerOrderBySeason_YearDesc(player).map(PlayerStatsMapper::toDto)
				.orElseThrow(() -> new RuntimeException("No stats found for this player"));
	}

	@Override
	public List<PlayerStatsDto> getTopScorers(int limit) {
		return psRepository.findAllByOrderByGoalsDesc().stream().limit(limit).map(PlayerStatsMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deletePlayerStats(Long id) {
		psRepository.deleteById(id);

	}

	@Override
	public List<PlayerStatsDto> getTopScorersBySeason(Long seasonId, int limit) {
		Pageable pageable = PageRequest.of(0, limit);
		List<PlayerStats> stats = psRepository.findTopScorersBySeason(seasonId, pageable);
		return stats.stream().map(PlayerStatsMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<PlayerStatsDto> getTopAssistsBySeason(Long seasonId, int limit) {
		Pageable pageable = PageRequest.of(0, limit);
		List<PlayerStats> stats = psRepository.findTopAssistersBySeason(seasonId, pageable);
		return stats.stream().map(PlayerStatsMapper::toDto).collect(Collectors.toList());
	}

}
