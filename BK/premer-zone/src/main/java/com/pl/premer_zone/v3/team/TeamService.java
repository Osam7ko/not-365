package com.pl.premer_zone.v3.team;

import java.util.List;

public interface TeamService {

	TeamDto createTeam(TeamDto dto);

	List<TeamDto> getAllTeams();

	TeamDto getTeamById(Long id);

	TeamDto updateTeam(Long id, TeamDto dto);

	void deleteTeam(Long id);
}
