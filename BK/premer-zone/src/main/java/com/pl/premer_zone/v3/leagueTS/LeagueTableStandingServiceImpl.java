package com.pl.premer_zone.v3.leagueTS;

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
public class LeagueTableStandingServiceImpl implements LeagueTableStandingService {

	private final LeagueTableStandingRepository repository;
	private final SeasonRepository seasonRepository;
	private final TeamRepository teamRepository;

	@Override
	public LeagueTableStandingDto create(LeagueTableStandingDto dto) {
		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));
		Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));

		LeagueTableStanding entity = LeagueTableStandingMapper.toEntity(dto, season, team);
		return LeagueTableStandingMapper.toDto(repository.save(entity));
	}

	@Override
	public LeagueTableStandingDto update(Long id, LeagueTableStandingDto dto) {
		LeagueTableStanding entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("LeagueTableStanding not found"));

		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));
		Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));

		entity.setSeason(season);
		entity.setTeam(team);
		entity.setPosition(dto.getPosition());
		entity.setPoints(dto.getPoints());

		return LeagueTableStandingMapper.toDto(repository.save(entity));
	}

	@Override
	public LeagueTableStandingDto getById(Long id) {
		LeagueTableStanding entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("LeagueTableStanding not found"));

		return LeagueTableStandingMapper.toDto(entity);
	}

	@Override
	public List<LeagueTableStandingDto> getAll() {
		return repository.findAll().stream().map(LeagueTableStandingMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public List<LeagueTableStandingDto> getBySeason(Long seasonId) {
		Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new RuntimeException("Season not found"));

		return repository.findAll().stream().filter(e -> e.getSeason().equals(season))
				.map(LeagueTableStandingMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
