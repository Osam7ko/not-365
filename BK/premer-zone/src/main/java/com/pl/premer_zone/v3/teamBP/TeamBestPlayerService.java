package com.pl.premer_zone.v3.teamBP;

import java.util.List;

public interface TeamBestPlayerService {

	TeamBestPlayerDto createTeamBestPlayer(TeamBestPlayerDto dto);

	List<TeamBestPlayerDto> getAll();

	List<TeamBestPlayerDto> getBySeason(Long seasonId);

	List<TeamBestPlayerDto> getByTeam(Long teamId);

	void deleteTeamBestPlayer(Long id);
}
