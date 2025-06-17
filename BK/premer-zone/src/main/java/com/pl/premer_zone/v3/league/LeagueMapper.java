package com.pl.premer_zone.v3.league;

public class LeagueMapper {

	public static LeagueDto toDto(League league) {
		if (league == null) {
			return null;
		}

		return LeagueDto.builder().id(league.getId()).name(league.getName()).logoUrl(league.getLogoUrl()).build();
	}

	public static League toEntity(LeagueDto dto) {
		if (dto == null) {
			return null;
		}

		return League.builder().id(dto.getId()).name(dto.getName()).logoUrl(dto.getLogoUrl()).build();
	}

}
