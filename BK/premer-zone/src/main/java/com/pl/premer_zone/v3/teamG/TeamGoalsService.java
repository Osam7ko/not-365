package com.pl.premer_zone.v3.teamG;

import java.util.List;

public interface TeamGoalsService {

	TeamGoalsDto create(TeamGoalsDto dto);

	TeamGoalsDto update(Long id, TeamGoalsDto dto);

	TeamGoalsDto getById(Long id);

	List<TeamGoalsDto> getAll();

	List<TeamGoalsDto> getBySeason(Long seasonId);

	void delete(Long id);
}
