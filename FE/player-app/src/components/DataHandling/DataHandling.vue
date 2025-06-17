<template>
  <div class="table-container" v-if="!loading && !error">
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Position</th>
          <th>Age</th>
          <th>Matches Played</th>
          <th>Starts</th>
          <th>Minutes Played</th>
          <th>Goals</th>
          <th>Assists</th>
          <th>Penalties Kicked</th>
          <th>Yellow Cards</th>
          <th>Red Cards</th>
          <th>Expected Goals (xG)</th>
          <th>Expected Assists (xAG)</th>
          <th>Team</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="player in playerData" :key="player.name">
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
  </div>

  <p v-else-if="loading">Loading...</p>
  <p v-else>Error: {{ error }}</p>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

const loading = ref(true);
const error = ref(null);
const playerData = ref([]);

onMounted(async () => {
  const params = new URLSearchParams(window.location.search);
  const teamValue = params.get("team");

  if (teamValue) {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/v1/player?team=${encodeURIComponent(
          teamValue
        )}`
      );
      playerData.value = res.data;
    } catch (err) {
      error.value = err.message;
    }
  }
  loading.value = false;
});
</script>

<style scoped src="./index.scss"></style>
