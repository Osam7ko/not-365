package com.pl.premer_zone.v3.player;

import java.util.List;

public interface PlayerService {

	PlayerDto createPlayer(PlayerDto dto);

	List<PlayerDto> getAllPlayers();

	PlayerDto getPlayerById(Long id);

	PlayerDto updatePlayer(Long id, PlayerDto dto);

	PlayerDto getPlayerWithLatestStats(Long playerid);

	void deletePlayer(Long id);
}
