package com.pl.premer_zone.v3.teamG;

import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.team.Team;

public class TeamGoalsMapper {
	public static TeamGoalsDto toDto(TeamGoals entity) {
		if (entity == null) {
			return null;
		}

		return TeamGoalsDto.builder().id(entity.getId())

				.teamId(entity.getTeam().getId()).teamName(entity.getTeam().getName())

				.seasonId(entity.getSeason().getId()).seasonYear(entity.getSeason().getYear())

				.goals(entity.getGoals()).build();
	}

	public static TeamGoals toEntity(TeamGoalsDto dto, Team team, Season season) {
		if (dto == null || team == null || season == null) {
			return null;
		}

		return TeamGoals.builder().id(dto.getId()).team(team).season(season).goals(dto.getGoals()).build();
	}
}
