package com.pl.premer_zone.v3.leagueBP;

import com.pl.premer_zone.v3.player.Player;
import com.pl.premer_zone.v3.season.Season;

public class LeagueBestPlayerMapper {

	public static LeagueBestPlayerDto toDto(LeagueBestPlayer lbp, String teamName) {
		return LeagueBestPlayerDto.builder().id(lbp.getId()).seasonId(lbp.getSeason().getId())
				.playerId(lbp.getPlayer().getId()).playerName(lbp.getPlayer().getName()).teamName(teamName)
				.role(lbp.getRole()).build();
	}

	public static LeagueBestPlayer toEntity(LeagueBestPlayerDto dto, Season season, Player player) {
		if (dto == null) {
			return null;
		}

		return LeagueBestPlayer.builder().id(dto.getId()).season(season).player(player).role(dto.getRole()).build();
	}

}
