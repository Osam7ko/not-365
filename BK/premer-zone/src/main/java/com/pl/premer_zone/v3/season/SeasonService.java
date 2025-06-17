package com.pl.premer_zone.v3.season;

import java.util.List;

public interface SeasonService {

	SeasonDto createSeason(SeasonDto dto);

	List<SeasonDto> getAllSeasons();

	SeasonDto getSeasonById(Long id);

	SeasonDto updateSeason(Long id, SeasonDto dto);

	void deleteSeason(Long id);
}
