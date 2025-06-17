package com.pl.premer_zone.v3.player;

import com.pl.premer_zone.v3.team.Team;

public class PlayerMapper {

	public static PlayerDto toDto(Player player, Team team) {
		return PlayerDto.builder().id(player.getId()).name(player.getName()).position(player.getPosition())
				.photoUrl(player.getPhotoUrl()).teamId(team != null ? team.getId() : null)
				.teamName(team != null ? team.getName() : null).build();
	}

	public static Player toEntity(PlayerDto dto) {
		return Player.builder().id(dto.getId()).name(dto.getName()).position(dto.getPosition())
				.photoUrl(dto.getPhotoUrl()).build();
	}
}
