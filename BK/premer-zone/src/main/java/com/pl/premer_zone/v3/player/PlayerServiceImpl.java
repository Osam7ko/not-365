package com.pl.premer_zone.v3.player;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pl.premer_zone.v3.playerST.PlayerStats;
import com.pl.premer_zone.v3.playerST.PlayerStatsRepository;
import com.pl.premer_zone.v3.team.Team;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

	private final PlayerRepository playerRepository;
	private final PlayerStatsRepository playerStatsRepository;

	@Override
	public PlayerDto createPlayer(PlayerDto dto) {
		Player player = PlayerMapper.toEntity(dto);
		player = playerRepository.save(player);
		return PlayerMapper.toDto(player, null);
	}

	@Override
	public List<PlayerDto> getAllPlayers() {
		List<Player> players = playerRepository.findAll();

		return players.stream().map(player -> PlayerMapper.toDto(player, null)).collect(Collectors.toList());
	}

	@Override
	public PlayerDto getPlayerById(Long id) {
		Player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));

		return PlayerMapper.toDto(player, null);
	}

	@Override
	public PlayerDto updatePlayer(Long id, PlayerDto dto) {
		Player existing = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));

		existing.setName(dto.getName());
		existing.setPosition(dto.getPosition());
		existing.setPhotoUrl(dto.getPhotoUrl());

		playerRepository.save(existing);

		return PlayerMapper.toDto(existing, null);
	}

	@Override
	public PlayerDto getPlayerWithLatestStats(Long playerId) {
		Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));

		PlayerStats latestStats = playerStatsRepository.findTopByPlayerOrderBySeason_YearDesc(player).orElse(null);

		Team team = (latestStats != null) ? latestStats.getTeam() : null;

		return PlayerMapper.toDto(player, team);
	}

	@Override
	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}

}
