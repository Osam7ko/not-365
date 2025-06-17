package com.pl.premer_zone.v3.season;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeasonDto {

	private Long id;

	private int year;

	private Long leagueId;

	private String leagueName;
}
