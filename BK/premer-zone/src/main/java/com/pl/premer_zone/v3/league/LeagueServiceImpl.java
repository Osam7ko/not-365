package com.pl.premer_zone.v3.league;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class LeagueServiceImpl implements LeagueService {

	private final LeagueRepository leagueRepository;

	public LeagueServiceImpl(LeagueRepository leagueRepository) {
		super();
		this.leagueRepository = leagueRepository;
	}

	@Override
	public LeagueDto getLeagueById(Long id) {
		League league = leagueRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("League not found with id " + id));
		return LeagueMapper.toDto(league);
	}

	@Override
	public List<LeagueDto> getAllLeagues() {
		return leagueRepository.findAll().stream().map(LeagueMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public LeagueDto createLeague(LeagueDto dto) {

		League league = LeagueMapper.toEntity(dto);
		League savedLeague = leagueRepository.save(league);
		return LeagueMapper.toDto(savedLeague);
	}

	@Override
	public LeagueDto updateLeague(Long id, LeagueDto dto) {
		League existing = leagueRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("League not found with id " + id));

		existing.setName(dto.getName());
		existing.setLogoUrl(dto.getLogoUrl());

		League updated = leagueRepository.save(existing);
		return LeagueMapper.toDto(updated);
	}

	@Override
	public void deleteLeague(Long id) {
		if (!leagueRepository.existsById(id)) {
			throw new RuntimeException("League not found with id " + id);
		}
		leagueRepository.deleteById(id);
	}

}
