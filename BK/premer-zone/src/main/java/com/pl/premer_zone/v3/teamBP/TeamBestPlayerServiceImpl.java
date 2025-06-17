package com.pl.premer_zone.v3.teamBP;

import java.util.List;
import java.util.stream.Collectors;

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
public class TeamBestPlayerServiceImpl implements TeamBestPlayerService {

	private final TeamBestPlayerRepository bpRepository;
	private final SeasonRepository seasonRepository;
	private final TeamRepository teamRepository;
	private final PlayerRepository playerRepository;

	@Override
	public TeamBestPlayerDto createTeamBestPlayer(TeamBestPlayerDto dto) {

		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));
		Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));
		Player player = playerRepository.findById(dto.getPlayerId())
				.orElseThrow(() -> new RuntimeException("Player not found"));

		TeamBestPlayer entity = TeamBestPlayer.builder().season(season).team(team).player(player).role(dto.getRole())
				.build();

		entity = bpRepository.save(entity);
		return TeamBestPlayerMapper.toDto(entity);
	}

	@Override
	public List<TeamBestPlayerDto> getAll() {
		return bpRepository.findAll().stream().map(TeamBestPlayerMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<TeamBestPlayerDto> getBySeason(Long seasonId) {
		Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new RuntimeException("Season not found"));

		return bpRepository.findAll().stream().filter(bp -> bp.getSeason().equals(season))
				.map(TeamBestPlayerMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<TeamBestPlayerDto> getByTeam(Long teamId) {
		Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));

		return bpRepository.findAll().stream().filter(bp -> bp.getTeam().equals(team)).map(TeamBestPlayerMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteTeamBestPlayer(Long id) {
		bpRepository.deleteById(id);

	}

}
