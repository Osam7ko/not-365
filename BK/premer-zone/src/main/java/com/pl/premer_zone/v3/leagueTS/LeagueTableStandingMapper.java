package com.pl.premer_zone.v3.leagueTS;

import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.team.Team;

public class LeagueTableStandingMapper {
	public static LeagueTableStandingDto toDto(LeagueTableStanding entity) {
		return LeagueTableStandingDto.builder().id(entity.getId()).seasonId(entity.getSeason().getId())
				.teamId(entity.getTeam().getId()).teamName(entity.getTeam().getName()).position(entity.getPosition())
				.points(entity.getPoints()).build();
	}

	public static LeagueTableStanding toEntity(LeagueTableStandingDto dto, Season season, Team team) {
		return LeagueTableStanding.builder().id(dto.getId()).season(season).team(team).position(dto.getPosition())
				.points(dto.getPoints()).build();
	}
}
