package com.pl.premer_zone.v3.teamS;

import java.util.List;

public interface TeamSeasonService {

	TeamSeasonDto createTeamSeason(TeamSeasonDto dto);

	List<TeamSeasonDto> getAll();

	TeamSeasonDto getById(Long id);

	List<TeamSeasonDto> getBySeason(Long seasonId);

	TeamSeasonDto updateTeamSeason(Long id, TeamSeasonDto dto);

	void deleteTeamSeason(Long id);
}
