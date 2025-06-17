package com.pl.premer_zone.v3.team;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.season.SeasonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

	private final TeamRepository teamRepository;
	private final SeasonRepository seasonRepository;

	@Override
	public TeamDto createTeam(TeamDto dto) {
		Team team = new Team();
		team.setName(dto.getName());
		team.setLogoUrl(dto.getLogoUrl());

		if (dto.getSeasonYears() != null && !dto.getSeasonYears().isEmpty()) {
			Set<Season> seasons = seasonRepository.findAll().stream()
					.filter(s -> dto.getSeasonYears().contains(s.getYear())).collect(Collectors.toSet());
			team.setSeasons(seasons);
		}

		team = teamRepository.save(team);
		return TeamMapper.toDto(team);
	}

	@Override
	public List<TeamDto> getAllTeams() {
		return teamRepository.findAll().stream().map(TeamMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public TeamDto getTeamById(Long id) {
		Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
		return TeamMapper.toDto(team);
	}

	@Override
	public TeamDto updateTeam(Long id, TeamDto dto) {
		Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));

		team.setName(dto.getName());
		team.setLogoUrl(dto.getLogoUrl());

		if (dto.getSeasonYears() != null) {
			Set<Season> seasons = seasonRepository.findAll().stream()
					.filter(s -> dto.getSeasonYears().contains(s.getYear())).collect(Collectors.toSet());
			team.setSeasons(seasons);
		}

		team = teamRepository.save(team);
		return TeamMapper.toDto(team);
	}

	@Override
	public void deleteTeam(Long id) {
		teamRepository.deleteById(id);
	}

}
