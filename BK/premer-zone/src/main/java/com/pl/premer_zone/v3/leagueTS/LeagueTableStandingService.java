package com.pl.premer_zone.v3.leagueTS;

import java.util.List;

public interface LeagueTableStandingService {

	LeagueTableStandingDto create(LeagueTableStandingDto dto);

	LeagueTableStandingDto update(Long id, LeagueTableStandingDto dto);

	LeagueTableStandingDto getById(Long id);

	List<LeagueTableStandingDto> getAll();

	List<LeagueTableStandingDto> getBySeason(Long seasonId);

	void delete(Long id);
}
