package com.pl.premer_zone.v3.league;

import java.util.List;

public interface LeagueService {

	List<LeagueDto> getAllLeagues();

	LeagueDto createLeague(LeagueDto dto);

	LeagueDto getLeagueById(Long id);

	LeagueDto updateLeague(Long id, LeagueDto dto);

	void deleteLeague(Long id);

}
