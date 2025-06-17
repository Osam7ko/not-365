package com.pl.premer_zone.v3.teamS;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.season.SeasonRepository;
import com.pl.premer_zone.v3.team.Team;
import com.pl.premer_zone.v3.team.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamSeasonServiceImpl implements TeamSeasonService {

	private final TeamSeasonRepository tsRepository;
	private final SeasonRepository seasonRepository;
	private final TeamRepository teamRepository;

	@Override
	public TeamSeasonDto createTeamSeason(TeamSeasonDto dto) {
		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));

		Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));

		TeamSeason entity = TeamSeasonMapper.toEntity(dto, season, team);

		entity = tsRepository.save(entity);
		return TeamSeasonMapper.toDto(entity);
	}

	@Override
	public List<TeamSeasonDto> getAll() {
		return tsRepository.findAll().stream().map(TeamSeasonMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public TeamSeasonDto getById(Long id) {
		TeamSeason entity = tsRepository.findById(id).orElseThrow(() -> new RuntimeException("TeamSeason not found"));
		return TeamSeasonMapper.toDto(entity);
	}

	@Override
	public List<TeamSeasonDto> getBySeason(Long seasonId) {
		Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new RuntimeException("Season not found"));

		return tsRepository.findAll().stream().filter(ts -> ts.getSeason().equals(season)).map(TeamSeasonMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public TeamSeasonDto updateTeamSeason(Long id, TeamSeasonDto dto) {
		TeamSeason entity = tsRepository.findById(id).orElseThrow(() -> new RuntimeException("TeamSeason not found"));

		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));

		Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));

		entity.setSeason(season);
		entity.setTeam(team);
		entity.setPosition(dto.getPosition());
		entity.setPoints(dto.getPoints());
		entity.setTotalGoals(dto.getTotalGoals());

		return TeamSeasonMapper.toDto(tsRepository.save(entity));
	}

	@Override
	public void deleteTeamSeason(Long id) {
		tsRepository.deleteById(id);
	}

}
