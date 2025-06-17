package com.pl.premer_zone.v3.leagueBP;

import java.util.List;

public interface LeagueBestPlayerService {

	LeagueBestPlayerDto createLeagueBestPlayer(LeagueBestPlayerDto dto);

	List<LeagueBestPlayerDto> getBestPlayersBySeason(Long seasonId);

	void deleteBySeasonId(Long seasonId);

}
