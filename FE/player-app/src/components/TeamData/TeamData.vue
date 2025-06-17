<template>
  <div class="fade-in" :class="{ loading }">
    <div class="table-container">
      <h1 class="page-title">
        <AnimatedLetters
          :letterClass="letterClass"
          :strArray="'Player Data'.split('')"
          :idx="12"
        />
      </h1>

      <table v-if="playerData.length > 0">
        <thead>
          <tr>
            <th @click="sortBy('name')">
              Name
              <span v-if="sortKey === 'name'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('pos')">
              Position
              <span v-if="sortKey === 'pos'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('age')">
              Age
              <span v-if="sortKey === 'age'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('mp')">
              Matches Played
              <span v-if="sortKey === 'mp'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('starts')">
              Starts
              <span v-if="sortKey === 'starts'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('min')">
              Minutes Played
              <span v-if="sortKey === 'min'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('gls')">
              Goals
              <span v-if="sortKey === 'gls'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('ast')">
              Assists
              <span v-if="sortKey === 'ast'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('pk')">
              Penalties Kicked
              <span v-if="sortKey === 'pk'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('crdy')">
              Yellow Cards
              <span v-if="sortKey === 'crdy'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('crdr')">
              Red Cards
              <span v-if="sortKey === 'crdr'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('xg')">
              Expected Goals (xG)
              <span v-if="sortKey === 'xg'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('xag')">
              Expected Assists (xAG)
              <span v-if="sortKey === 'xag'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
            <th @click="sortBy('team')">
              Team
              <span v-if="sortKey === 'team'">{{
                sortOrder === "asc" ? "▲" : "▼"
              }}</span>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="player in visiblePlayers" :key="player.name">
            <td>{{ player.name }}</td>
            <td>{{ player.pos }}</td>
            <td>{{ player.age }}</td>
            <td>{{ player.mp }}</td>
            <td>{{ player.starts }}</td>
            <td>{{ player.min }}</td>
            <td>{{ player.gls }}</td>
            <td>{{ player.ast }}</td>
            <td>{{ player.pk }}</td>
            <td>{{ player.crdy }}</td>
            <td>{{ player.crdr }}</td>
            <td>{{ player.xg }}</td>
            <td>{{ player.xag }}</td>
            <td>{{ player.team }}</td>
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
import axios from "axios";
import AnimatedLetters from "@/components/AnimatedLetters/AnimatedLetters.vue";

const loading = ref(true);
const error = ref(null);
const playerData = ref([]);
const playersToShow = ref(10);
const letterClass = ref("text-animate");

const sortKey = ref(null);
const sortOrder = ref("asc");

const sortBy = (key) => {
  if (sortKey.value === key) {
    sortOrder.value = sortOrder.value === "asc" ? "desc" : "asc";
  } else {
    sortKey.value = key;
    sortOrder.value = "asc";
  }
};

const sortedPlayers = computed(() => {
  if (!sortKey.value) return playerData.value;

  return [...playerData.value].sort((a, b) => {
    const valA = a[sortKey.value] ?? 0;
    const valB = b[sortKey.value] ?? 0;

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

const fetchData = async () => {
  try {
    const params = new URLSearchParams(window.location.search);
    const queryParams = ["team", "nation", "position", "name"];

    for (const param of queryParams) {
      const value = params.get(param);
      if (value) {
        const res = await axios.get(
          `http://localhost:8080/api/v1/player?${param}=${encodeURIComponent(
            value
          )}`
        );
        playerData.value = res.data;
        break;
      }
    }
  } catch (err) {
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  fetchData();
});

const showMore = () => {
  playersToShow.value += 10;
};
</script>

<style scoped src="./index.scss"></style>
