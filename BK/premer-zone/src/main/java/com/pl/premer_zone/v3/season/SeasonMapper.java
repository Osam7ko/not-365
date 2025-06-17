package com.pl.premer_zone.v3.season;

import com.pl.premer_zone.v3.league.League;

public class SeasonMapper {
	public static SeasonDto toDto(Season season) {
		if (season == null) {
			return null;
		}

		return SeasonDto.builder().id(season.getId()).year(season.getYear()).leagueId(season.getLeague().getId())
				.leagueName(season.getLeague().getName()).build();
	}

	public static Season toEntity(SeasonDto dto, League league) {
		if (dto == null || league == null) {
			return null;
		}

		return Season.builder().id(dto.getId()).year(dto.getYear()).league(league).build();
	}
}
