package com.pl.premer_zone.v3.playerST;

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
public class PlayerStatsDto {

	private Long id;

	private Long playerId;
	private String playerName;

	private Long teamId;
	private String teamName;

	private Long seasonId;
	private int seasonYear;

	private int goals;
	private int assists;
}
