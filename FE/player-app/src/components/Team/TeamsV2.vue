<template>
  <div class="container teams-page">
    <h1 class="page-title">
      <AnimatedLetters
        :letterClass="letterClass"
        :strArray="'Teams V2'.split('')"
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
      :leagueTopFull="
        seasonStats[selectedSeason]?.league_table.concat(extraTeams) || []
      "
    />
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from "vue";
import AnimatedLetters from "@/components/AnimatedLetters/AnimatedLetters.vue";
import teamData from "@/data/teams.json";
import seasonStats from "@/data/season_stats_full.json";
import SeasonHighlights from "./SeasonHighlights.vue";

const teams = ref([]);
const teamStats = ref([]);
const searchQuery = ref("");
const selectedSeason = ref(2024);
const warningMessage = ref("");
const letterClass = ref("text-animate");

onMounted(() => {
  setTimeout(() => {
    letterClass.value = "text-animate-hover";
  }, 3000);
  loadTeamsAndStats();
});

watch(selectedSeason, async () => {
  await loadTeamsAndStats();
});

const loadTeamsAndStats = async () => {
  teams.value = teamData.teams;

  const statsBySeason = seasonStats[selectedSeason.value];
  if (statsBySeason && statsBySeason.team_goals) {
    teamStats.value = Object.entries(statsBySeason.team_goals).map(
      ([teamName, goals]) => {
        const bestPlayers =
          statsBySeason.best_players?.teams_best?.[teamName] || [];
        return {
          name: teamName,
          bestPlayer1: bestPlayers[0]?.player || null,
          bestPlayer2: bestPlayers[1]?.player || null,
          totalGoals: goals,
        };
      }
    );
  } else {
    teamStats.value = [];
  }
};

const filteredTeamsWithStats = computed(() => {
  const activeTeams = Object.keys(
    seasonStats[selectedSeason.value]?.team_goals || {}
  );

  return teams.value
    .filter(
      (team) =>
        team.title.toLowerCase().includes(searchQuery.value.toLowerCase()) &&
        activeTeams.includes(team.title)
    )
    .map((team) => {
      const stats = teamStats.value.find((s) => s.name === team.title);
      return {
        ...team,
        bestPlayer1: stats?.bestPlayer1 || null,
        bestPlayer2: stats?.bestPlayer2 || null,
        totalGoals: stats?.totalGoals || 0,
      };
    });
});
</script>

<style scoped src="../Team/index-v2.scss"></style>
