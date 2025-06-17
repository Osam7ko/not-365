package com.pl.premer_zone.v3.leagueTS;

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
public class LeagueTableStandingDto {

	private Long id;
	private Long seasonId;
	private Long teamId;
	private String teamName;
	private int position;
	private int points;

}
