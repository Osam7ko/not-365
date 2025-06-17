package com.pl.premer_zone.v3.team;

import java.util.Set;
import java.util.stream.Collectors;

import com.pl.premer_zone.v3.season.Season;

public class TeamMapper {
	public static TeamDto toDto(Team team) {
		if (team == null) {
			return null;
		}

		Set<Integer> seasonYears = team.getSeasons() != null
				? team.getSeasons().stream().map(Season::getYear).collect(Collectors.toSet())
				: Set.of();

		return TeamDto.builder().id(team.getId()).name(team.getName()).logoUrl(team.getLogoUrl())
				.seasonYears(seasonYears).build();
	}
}
