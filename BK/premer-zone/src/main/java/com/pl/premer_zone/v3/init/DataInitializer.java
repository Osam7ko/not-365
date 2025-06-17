package com.pl.premer_zone.v3.init;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pl.premer_zone.v3.data.dto.LeagueBestPlayerEntry;
import com.pl.premer_zone.v3.data.dto.LeagueTableEntry;
import com.pl.premer_zone.v3.data.dto.PlayerStatEntry;
import com.pl.premer_zone.v3.data.dto.SeasonData;
import com.pl.premer_zone.v3.data.dto.TeamBestPlayerEntry;
import com.pl.premer_zone.v3.league.League;
import com.pl.premer_zone.v3.league.LeagueRepository;
import com.pl.premer_zone.v3.leagueBP.LeagueBestPlayer;
import com.pl.premer_zone.v3.leagueBP.LeagueBestPlayerRepository;
import com.pl.premer_zone.v3.leagueTS.LeagueTableStanding;
import com.pl.premer_zone.v3.leagueTS.LeagueTableStandingRepository;
import com.pl.premer_zone.v3.player.Player;
import com.pl.premer_zone.v3.player.PlayerRepository;
import com.pl.premer_zone.v3.playerST.PlayerStats;
import com.pl.premer_zone.v3.playerST.PlayerStatsRepository;
import com.pl.premer_zone.v3.season.Season;
import com.pl.premer_zone.v3.season.SeasonRepository;
import com.pl.premer_zone.v3.team.Team;
import com.pl.premer_zone.v3.team.TeamRepository;
import com.pl.premer_zone.v3.teamBP.TeamBestPlayer;
import com.pl.premer_zone.v3.teamBP.TeamBestPlayerRepository;
import com.pl.premer_zone.v3.teamG.TeamGoals;
import com.pl.premer_zone.v3.teamG.TeamGoalsRepository;
import com.pl.premer_zone.v3.teamS.TeamSeason;
import com.pl.premer_zone.v3.teamS.TeamSeasonRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	private final ObjectMapper objectMapper;

	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;
	private final SeasonRepository seasonRepository;
	private final LeagueRepository leagueRepository;

	private final PlayerStatsRepository playerStatsRepository;
	private final LeagueBestPlayerRepository leagueBestPlayerRepository;
	private final TeamBestPlayerRepository teamBestPlayerRepository;
	private final LeagueTableStandingRepository leagueTableStandingRepository;
	private final TeamGoalsRepository teamGoalsRepository;
	private final TeamSeasonRepository tsRepository;

	@Override
	public void run(String... args) throws Exception {

//		if (seasonRepository.count() > 0) {
//			System.out.println("🔁 Skipping DataInitializer: data already exists.");
//			return;
//		}

		InputStream inputStream = new ClassPathResource("data/premier_league_data.json").getInputStream();

		TypeReference<Map<String, SeasonData>> typeRef = new TypeReference<>() {
		};
		Map<String, SeasonData> dataMap = objectMapper.readValue(inputStream, typeRef);

		// 👇 نبدأ بإضافة الدوري أولًا لأنه مرتبط بـ Season
		League league = leagueRepository.findByName("Premier League")
				.orElseGet(() -> leagueRepository.save(new League(null, "Premier League", null)));

		for (Map.Entry<String, SeasonData> entry : dataMap.entrySet()) {
			int year = Integer.parseInt(entry.getKey());
			SeasonData seasonData = entry.getValue();

			// ✅ إنشاء Season
			Season season = new Season();
			season.setYear(year);
			season.setLeague(league);
			season = seasonRepository.save(season);

			// ✅ إنشاء الفرق
			Map<String, Team> teamMap = new HashMap<>();
			for (String teamName : seasonData.getTeam_goals().keySet()) {
				Team team = teamRepository.findByName(teamName)
						.orElseGet(() -> teamRepository.save(new Team(null, teamName, null, new HashSet<>())));

				team = teamRepository.save(team);
				teamMap.put(teamName, team);

			}

			// ✅ أهداف الفرق
			for (Map.Entry<String, Integer> tgEntry : seasonData.getTeam_goals().entrySet()) {
				String teamName = tgEntry.getKey();
				int goals = tgEntry.getValue();

				Team team = teamMap.get(teamName);
				if (team == null) {
					continue;
				}

				TeamGoals teamGoals = TeamGoals.builder().season(season).team(team).goals(goals).build();

				teamGoalsRepository.save(teamGoals);
			}

			// ✅ اللاعبين والإحصائيات
			Map<String, Player> playerMap = new HashMap<>();
			for (Map.Entry<String, List<PlayerStatEntry>> squadEntry : seasonData.getSquad_stats_by_team().entrySet()) {
				String teamName = squadEntry.getKey();
				Team team = teamMap.get(teamName);
				if (team == null) {
					continue;
				}

				for (PlayerStatEntry pse : squadEntry.getValue()) {
					String playerName = pse.getPlayer();
					int goals = pse.getGoals();
					int assists = pse.getAssists();

					Player player = playerRepository.findByName(playerName).orElse(null);

					if (player == null) {
						player = new Player();
						player.setName(playerName);
						player = playerRepository.save(player);
					}

					playerMap.put(playerName, player);

					PlayerStats stats = PlayerStats.builder().player(player).team(team).season(season).goals(goals)
							.assists(assists).build();

					playerStatsRepository.save(stats);
				}
			}
			for (LeagueTableEntry lte : seasonData.getLeague_table()) {
				String teamName = lte.getTeam();
				int position = lte.getPosition();
				int points = lte.getPoints();

				Team team = teamMap.get(teamName);
				if (team == null) {
					continue;
				}

				LeagueTableStanding standing = LeagueTableStanding.builder().season(season).team(team)
						.position(position).points(points).build();

				leagueTableStandingRepository.save(standing);
				TeamSeason teamSeason = TeamSeason.builder().season(season).team(team).position(position).points(points)
						.totalGoals(seasonData.getTeam_goals().getOrDefault(teamName, 0)) // جلب عدد الأهداف إن وجد
						.build();

				tsRepository.save(teamSeason);

			}
			if (seasonData.getBest_players() != null && seasonData.getBest_players().getLeague_best() != null) {
				LeagueBestPlayerEntry entry1 = seasonData.getBest_players().getLeague_best();
				if (entry1 != null) {
					String playerName = entry1.getPlayer();
					Player player = playerMap.get(playerName);
					if (player != null) {
						LeagueBestPlayer bestPlayer = LeagueBestPlayer.builder().season(season).player(player).build();

						leagueBestPlayerRepository.save(bestPlayer);
					}
				}

			}
			if (seasonData.getBest_players() != null && seasonData.getBest_players().getTeams_best() != null) {
				for (Map.Entry<String, List<TeamBestPlayerEntry>> teamEntry : seasonData.getBest_players()
						.getTeams_best().entrySet()) {
					String teamName = teamEntry.getKey();
					Team team = teamMap.get(teamName);
					if (team == null) {
						continue;
					}

					for (TeamBestPlayerEntry entry11 : teamEntry.getValue()) {
						String playerName = entry11.getPlayer();
						String role = entry11.getRole();

						Player player = playerMap.get(playerName);
						if (player == null) {
							continue;
						}

						TeamBestPlayer tbp = TeamBestPlayer.builder().season(season).team(team).player(player)
								.role(role).build();

						teamBestPlayerRepository.save(tbp);
					}
				}
			}

		}

	}
}