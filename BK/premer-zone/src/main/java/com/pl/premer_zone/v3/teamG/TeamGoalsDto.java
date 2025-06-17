package com.pl.premer_zone.v3.teamG;

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
public class TeamGoalsDto {

	private Long id;

	private Long teamId;
	private String teamName;

	private Long seasonId;
	private int seasonYear;

	private int goals;

}
