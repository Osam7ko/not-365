package com.pl.premer_zone.v3.season;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pl.premer_zone.v3.league.League;
import com.pl.premer_zone.v3.league.LeagueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

	private final SeasonRepository seasonRepository;
	private final LeagueRepository leagueRepository;

	@Override
	public SeasonDto createSeason(SeasonDto dto) {
		League league = leagueRepository.findById(dto.getLeagueId())
				.orElseThrow(() -> new RuntimeException("League not found"));

		Season season = SeasonMapper.toEntity(dto, league);
		season = seasonRepository.save(season);

		return SeasonMapper.toDto(season);
	}

	@Override
	public List<SeasonDto> getAllSeasons() {
		return seasonRepository.findAll().stream().map(SeasonMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public SeasonDto getSeasonById(Long id) {
		Season season = seasonRepository.findById(id).orElseThrow(() -> new RuntimeException("League not found"));

		return SeasonMapper.toDto(season);
	}

	@Override
	public SeasonDto updateSeason(Long id, SeasonDto dto) {
		Season season = seasonRepository.findById(id).orElseThrow(() -> new RuntimeException("Season not found"));

		League league = leagueRepository.findById(dto.getLeagueId())
				.orElseThrow(() -> new RuntimeException("League not found"));

		season.setYear(dto.getYear());
		season.setLeague(league);

		season = seasonRepository.save(season);
		return SeasonMapper.toDto(season);
	}

	@Override
	public void deleteSeason(Long id) {
		seasonRepository.deleteById(id);
	}

}
