<template>
  <div class="fade-in" :class="{ loading }">
    <div class="table-container">
      <div class="team-header">
        <img
          v-if="teamLogo"
          :src="teamLogo"
          :alt="teamName"
          class="team-logo"
        />
        <h1 class="page-title">{{ teamName }}</h1>
      </div>

      <table v-if="players.length > 0">
        <thead>
          <tr>
            <th
              v-for="header in headers"
              :key="header.key"
              @click="sortBy(header.key)"
            >
              {{ header.label }}
              <span v-if="sortKey === header.key">
                {{ sortOrder === "asc" ? "▲" : "▼" }}
              </span>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="player in visiblePlayers" :key="player.name">
            <td>{{ player.name }}</td>
            <td>{{ player.age }}</td>
            <td>{{ player.nationality }}</td>
            <td>{{ player.position }}</td>
            <td>{{ player.appearances }}</td>
            <td>{{ player.goals }}</td>
            <td>{{ player.assists }}</td>
          </tr>
        </tbody>
      </table>

      <p v-if="error" class="error-message">Error: {{ error }}</p>
      <p v-if="loading">Loading...</p>

      <button
        v-if="visiblePlayers.length < sortedPlayers.length"
        @click="showMore"
        class="show-more-button"
      >
        Show More
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useRoute } from "vue-router";
import AnimatedLetters from "@/components/AnimatedLetters/AnimatedLetters.vue";
import { getPlayersByTeamAndSeason } from "@/service/PlayerService";

const players = ref([]);
const playersToShow = ref(10);
const sortKey = ref(null);
const sortOrder = ref("asc");
const loading = ref(true);
const error = ref(null);
const letterClass = ref("text-animate");

const headers = [
  { key: "name", label: "Name" },
  { key: "age", label: "Age" },
  { key: "nationality", label: "Nationality" },
  { key: "position", label: "Position" },
  { key: "appearances", label: "Apps" },
  { key: "goals", label: "Goals" },
  { key: "assists", label: "Assists" },
];

const route = useRoute();
const teamId = parseInt(route.query.teamId);
const season = parseInt(route.query.season) || 2023;
const teamName = ref(route.query.teamName || "Team");
const teamLogo = ref(route.query.teamLogo || "");

const fetchPlayers = async () => {
  try {
    loading.value = true;
    players.value = await getPlayersByTeamAndSeason(teamId, season);
  } catch (err) {
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  setTimeout(() => (letterClass.value = "text-animate-hover"), 3000);
  fetchPlayers();
});

const sortBy = (key) => {
  if (sortKey.value === key) {
    sortOrder.value = sortOrder.value === "asc" ? "desc" : "asc";
  } else {
    sortKey.value = key;
    sortOrder.value = "asc";
  }
};

const sortedPlayers = computed(() => {
  if (!sortKey.value) return players.value;
  return [...players.value].sort((a, b) => {
    const valA = a[sortKey.value] ?? "";
    const valB = b[sortKey.value] ?? "";
    return sortOrder.value === "asc"
      ? valA > valB
        ? 1
        : -1
      : valA < valB
      ? 1
      : -1;
  });
});

const visiblePlayers = computed(() =>
  sortedPlayers.value.slice(0, playersToShow.value)
);

const showMore = () => {
  playersToShow.value += 10;
};
</script>

<style scoped src="./indexv2.scss"></style>
