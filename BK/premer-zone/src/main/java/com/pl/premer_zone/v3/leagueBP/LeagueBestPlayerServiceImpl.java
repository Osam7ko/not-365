package com.pl.premer_zone.v3.leagueBP;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pl.premer_zone.v3.player.Player;
import com.pl.premer_zone.v3.player.PlayerRepository;
import com.pl.premer_zone.v3.playerST.PlayerStatsRepository;
import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.season.SeasonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeagueBestPlayerServiceImpl implements LeagueBestPlayerService {

	private final LeagueBestPlayerRepository lbpRepository;
	private final SeasonRepository seasonRepository;
	private final PlayerRepository playerRepository;
	private final PlayerStatsRepository playerStatsRepository;

	@Override
	public LeagueBestPlayerDto createLeagueBestPlayer(LeagueBestPlayerDto dto) {
		Season season = seasonRepository.findById(dto.getSeasonId())
				.orElseThrow(() -> new RuntimeException("Season not found"));
		Player player = playerRepository.findById(dto.getPlayerId())
				.orElseThrow(() -> new RuntimeException("Season not found"));

		LeagueBestPlayer lbp = LeagueBestPlayerMapper.toEntity(dto, season, player);
		lbp = lbpRepository.save(lbp);

		String teamName = playerStatsRepository.findFirstByPlayerAndSeason(player, season)
				.map(stats -> stats.getTeam().getName()).orElse("Unknown");

		return LeagueBestPlayerMapper.toDto(lbp, teamName);
	}

	@Override
	public List<LeagueBestPlayerDto> getBestPlayersBySeason(Long seasonId) {
		Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new RuntimeException("Season not found"));

		List<LeagueBestPlayer> list = lbpRepository.findBySeason(season);

		return list.stream().map(lbp -> {
			String teamName = playerStatsRepository.findFirstByPlayerAndSeason(lbp.getPlayer(), season)
					.map(stats -> stats.getTeam().getName()).orElse("Unknown");

			return LeagueBestPlayerMapper.toDto(lbp, teamName);
		}).collect(Collectors.toList());
	}

	@Override
	public void deleteBySeasonId(Long seasonId) {
		Season season = seasonRepository.findById(seasonId).orElseThrow(() -> new RuntimeException("Season not found"));
		lbpRepository.deleteBySeason(season);

	}

}
