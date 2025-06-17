package com.pl.premer_zone.v3.playerST;

import java.util.List;
import java.util.stream.Collectors;

import com.pl.premer_zone.v3.player.Player;
import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.team.Team;

public class PlayerStatsMapper {
	public static PlayerStatsDto toDto(PlayerStats stats) {
		return PlayerStatsDto.builder().id(stats.getId()).playerId(stats.getPlayer().getId())
				.playerName(stats.getPlayer().getName())

				.teamId(stats.getTeam().getId()).teamName(stats.getTeam().getName())

				.seasonId(stats.getSeason().getId()).seasonYear(stats.getSeason().getYear())

				.goals(stats.getGoals()).assists(stats.getAssists()).build();
	}

	public static PlayerStats toEntity(PlayerStatsDto dto, Player player, Team team, Season season) {
		return PlayerStats.builder().id(dto.getId()).player(player).team(team).season(season).goals(dto.getGoals())
				.assists(dto.getAssists()).build();
	}

	public static List<PlayerStatsDto> toDtoList(List<PlayerStats> statsList) {
		return statsList.stream().map(PlayerStatsMapper::toDto).collect(Collectors.toList());
	}

}
