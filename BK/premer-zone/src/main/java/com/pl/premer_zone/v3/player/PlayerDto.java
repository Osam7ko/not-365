package com.pl.premer_zone.v3.player;

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
public class PlayerDto {

	private Long id;

	private String name;

	private String position;

	private Long teamId;

	private String teamName;

	private String photoUrl;
}
