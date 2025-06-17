package com.pl.premer_zone.v3.teamS;

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
public class TeamSeasonDto {

	private Long id;

	private Long seasonId;
	private int seasonYear;

	private Long teamId;
	private String teamName;

	private int position;
	private int points;
	private int totalGoals;

}
