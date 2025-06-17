package com.pl.premer_zone.v3.teamG;

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
public class TeamGoalsServiceImpl implements TeamGoalsService {

	private final TeamGoalsRepository tgRepository;
	private final TeamRepository teamRepository;
	private final SeasonRepository seasonRepository;

	@Override
	public TeamGoalsDto create(TeamGoalsDto dto) {
		Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));

		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));

		TeamGoals entity = TeamGoals.builder().team(team).season(season).goals(dto.getGoals()).build();

		entity = tgRepository.save(entity);
		return TeamGoalsMapper.toDto(entity);
	}

	@Override
	public TeamGoalsDto update(Long id, TeamGoalsDto dto) {
		TeamGoals entity = tgRepository.findById(id).orElseThrow(() -> new RuntimeException("TeamGoals not found"));

		Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));
		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));

		entity.setTeam(team);
		entity.setSeason(season);
		entity.setGoals(dto.getGoals());

		return TeamGoalsMapper.toDto(tgRepository.save(entity));
	}

	@Override
	public TeamGoalsDto getById(Long id) {
		TeamGoals entity = tgRepository.findById(id).orElseThrow(() -> new RuntimeException("TeamGoals not found"));
		return TeamGoalsMapper.toDto(entity);
	}

	@Override
	public List<TeamGoalsDto> getAll() {
		return tgRepository.findAll().stream().map(TeamGoalsMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<TeamGoalsDto> getBySeason(Long seasonId) {
		Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new RuntimeException("Season not found"));

		return tgRepository.findAll().stream().filter(tg -> tg.getSeason().equals(season)).map(TeamGoalsMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		tgRepository.deleteById(id);
	}
}
