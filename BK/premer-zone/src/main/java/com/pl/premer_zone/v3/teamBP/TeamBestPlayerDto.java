package com.pl.premer_zone.v3.teamBP;

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
public class TeamBestPlayerDto {

	private Long id;

	private Long seasonId;
	private int seasonYear;

	private Long teamId;
	private String teamName;

	private Long playerId;
	private String playerName;

	private String role;

}
