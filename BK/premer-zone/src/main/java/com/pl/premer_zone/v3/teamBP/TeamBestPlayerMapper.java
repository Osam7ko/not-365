package com.pl.premer_zone.v3.teamBP;

import com.pl.premer_zone.v3.player.Player;
import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.team.Team;

public class TeamBestPlayerMapper {
	public static TeamBestPlayerDto toDto(TeamBestPlayer entity) {
		if (entity == null) {
			return null;
		}

		return TeamBestPlayerDto.builder().id(entity.getId())

				.seasonId(entity.getSeason().getId()).seasonYear(entity.getSeason().getYear())

				.teamId(entity.getTeam().getId()).teamName(entity.getTeam().getName())

				.playerId(entity.getPlayer().getId()).playerName(entity.getPlayer().getName())

				.role(entity.getRole()).build();
	}

	public static TeamBestPlayer toEntity(TeamBestPlayerDto dto, Season season, Team team, Player player) {
		if (dto == null || season == null || team == null || player == null) {
			return null;
		}

		return TeamBestPlayer.builder().id(dto.getId()).season(season).team(team).player(player).role(dto.getRole())
				.build();
	}
}
