package com.pl.premer_zone.v3.teamS;

import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.team.Team;

public class TeamSeasonMapper {
	public static TeamSeasonDto toDto(TeamSeason entity) {
		if (entity == null) {
			return null;
		}

		return TeamSeasonDto.builder().id(entity.getId()).seasonId(entity.getSeason().getId())
				.seasonYear(entity.getSeason().getYear()).teamId(entity.getTeam().getId())
				.teamName(entity.getTeam().getName()).position(entity.getPosition()).points(entity.getPoints())
				.totalGoals(entity.getTotalGoals()).build();

	}

	public static TeamSeason toEntity(TeamSeasonDto dto, Season season, Team team) {
		if (dto == null || season == null || team == null) {
			return null;
		}

		return TeamSeason.builder().id(dto.getId()).season(season).team(team).position(dto.getPosition())
				.points(dto.getPoints()).totalGoals(dto.getTotalGoals()).build();
	}

}
