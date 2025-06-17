package com.pl.premer_zone.v3.data.dto;

import java.util.List;
import java.util.Map;

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
public class BestPlayers {

	private LeagueBestPlayerEntry league_best;
	private Map<String, List<TeamBestPlayerEntry>> teams_best;
}
