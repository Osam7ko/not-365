<template>
  <div class="container teams-page">
    <h1 class="page-title">
      <AnimatedLetters
        :letterClass="letterClass"
        :strArray="'Teams V3'.split('')"
        :idx="15"
      />
    </h1>

    <div class="top-controls">
      <div class="search-bar">
        <input
          type="text"
          placeholder="Search for teams"
          v-model="searchQuery"
        />
      </div>
      <div class="season-select">
        <p v-if="warningMessage" class="warning-msg">{{ warningMessage }}</p>
        <label for="season">Season:</label>
        <select v-model="selectedSeason" id="season">
          <option
            v-for="year in [2021, 2022, 2023, 2024]"
            :key="year"
            :value="year"
          >
            {{ year }}
          </option>
        </select>
      </div>
    </div>

    <div class="images-container">
      <div
        class="image-box"
        v-for="(team, idx) in filteredTeamsWithStats"
        :key="idx"
      >
        <img :src="team.cover" :alt="team.title" class="teams-image" />
        <div class="team-info">
          <div class="content">
            <p class="title">{{ team.title }}</p>
            <p class="sub">Best Player 1: {{ team.bestPlayer1 || "N/A" }}</p>
            <p class="sub">Best Player 2: {{ team.bestPlayer2 || "N/A" }}</p>
            <p class="sub">Total Goals: {{ team.totalGoals }}</p>
            <router-link
              class="btn"
              :to="{
                path: '/data-v2',
                query: {
                  teamName: team.title,
                  teamLogo: team.cover,
                  season: selectedSeason,
                },
              }"
            >
              View
            </router-link>
          </div>
        </div>
      </div>
    </div>
    <SeasonHighlights
      :season="selectedSeason"
      :topScorers="seasonStats[selectedSeason]?.top_players_by_goals || []"
      :topAssists="seasonStats[selectedSeason]?.top_players_by_assists || []"
      :leagueTop="seasonStats[selectedSeason]?.league_table || []"
      :leagueTopFull="seasonStats[selectedSeason]?.league_table || []"
    />
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from "vue";
import AnimatedLetters from "@/components/AnimatedLetters/AnimatedLetters.vue";
import SeasonHighlights from "./SeasonHighlights.vue";

import { getAllSeasons } from "@/service/v3/SeasonService";
import { getAllTeams } from "@/service/v3/TeamService";
import {
  getTopScorersBySeason,
  getTopAssistsBySeason,
  getLeagueTableBySeason,
  getTeamGoalsBySeason,
} from "@/service/v3/TeamStatsService";

// State
const searchQuery = ref("");
const warningMessage = ref("");
const letterClass = ref("text-animate");
const selectedSeason = ref(2024);
const seasonStats = ref({});
const allSeasons = ref([]);
const currentSeasonId = ref(null);
const leagueId = 1;
const teams = ref([]);
const teamStats = ref([]);

// Normalization function
const normalize = (name) =>
  name.toLowerCase().replace(/\s+/g, "").replaceAll(".", "");

// Load on mount
onMounted(() => {
  setTimeout(() => {
    letterClass.value = "text-animate-hover";
  }, 3000);
  loadTeamsAndStats();
});

// Watch season change
watch(selectedSeason, async () => {
  await loadTeamsAndStats();
});

// Load everything
const loadTeamsAndStats = async () => {
  try {
    // Get seasons if empty
    if (allSeasons.value.length === 0) {
      allSeasons.value = await getAllSeasons();
    }

    const season = allSeasons.value.find(
      (s) => s.year === selectedSeason.value && s.leagueId === leagueId
    );

    if (!season) {
      warningMessage.value = "Season not found for selected year and league";
      return;
    }

    currentSeasonId.value = season.id;

    // Get teams
    const res = await getAllTeams();
    teams.value = res.map((team) => ({
      ...team,
      normalizedName: normalize(team.name),
      cover: `/teams/${normalize(team.name)}.png`,
      title: team.name,
    }));

    // Get stats
    const [goals, topScorers, topAssists, leagueTable] = await Promise.all([
      getTeamGoalsBySeason(currentSeasonId.value),
      getTopScorersBySeason(currentSeasonId.value),
      getTopAssistsBySeason(currentSeasonId.value),
      getLeagueTableBySeason(currentSeasonId.value),
    ]);

    const teamGoalsMap = {};
    goals.forEach((item) => {
      teamGoalsMap[normalize(item.teamName)] = item.goals;
    });

    const bestPlayersMap = {};
    topScorers.forEach((p) => {
      const teamKey = normalize(p.teamName);
      bestPlayersMap[teamKey] = bestPlayersMap[teamKey] || [];
      bestPlayersMap[teamKey].push({ player: p.playerName });
    });
    topAssists.forEach((p) => {
      const teamKey = normalize(p.teamName);
      bestPlayersMap[teamKey] = bestPlayersMap[teamKey] || [];
      bestPlayersMap[teamKey].push({ player: p.playerName });
    });

    seasonStats.value[selectedSeason.value] = {
      team_goals: teamGoalsMap,
      best_players: {
        teams_best: bestPlayersMap,
      },
      league_table: leagueTable,
      top_players_by_goals: topScorers,
      top_players_by_assists: topAssists,
    };

    console.log("✅ GOT goals from API:", goals);

    teamStats.value = teams.value
      .filter((team) => {
        const match = teamGoalsMap[team.normalizedName];
        if (!match) console.warn("⚠️ No goals for:", team.name);
        return match !== undefined;
      })
      .map((team) => {
        const key = team.normalizedName;
        const bestPlayers = bestPlayersMap[key] || [];
        return {
          name: team.name,
          title: team.title,
          cover: team.cover,
          bestPlayer1: bestPlayers[0]?.player || null,
          bestPlayer2: bestPlayers[1]?.player || null,
          totalGoals: teamGoalsMap[key] || 0,
        };
      });
  } catch (err) {
    warningMessage.value = "Failed to load data";
    console.error("🔥 Error in loadTeamsAndStats:", err);
    teams.value = [];
    teamStats.value = [];
  }
};

// Filtering
const filteredTeamsWithStats = computed(() =>
  teamStats.value.filter((team) =>
    team.title.toLowerCase().includes(searchQuery.value.toLowerCase())
  )
);
</script>

<style scoped src="../Team/index-v2.scss"></style>
