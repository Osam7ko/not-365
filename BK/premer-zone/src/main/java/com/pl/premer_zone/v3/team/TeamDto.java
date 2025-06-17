package com.pl.premer_zone.v3.team;

import java.util.Set;

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
public class TeamDto {

	private Long id;

	private String name;

	private String logoUrl;

//	for the Team Seasons
	private Set<Integer> seasonYears;
}
