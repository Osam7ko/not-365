package com.pl.premer_zone.v3.leagueBP;

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
public class LeagueBestPlayerDto {

	private Long id;
	private Long seasonId;
	private Long playerId;
	private String playerName;
	private String teamName;
	private String role;

}
