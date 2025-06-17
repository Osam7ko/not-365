<template>
  <div class="container teams-page">
    <h1 class="page-title">
      <AnimatedLetters
        :letterClass="letterClass"
        :strArray="'Teams'.split('')"
        :idx="15"
      />
    </h1>

    <div class="search-bar">
      <input type="text" placeholder="Search for teams" v-model="searchQuery" />
    </div>

    <div class="images-container">
      <div class="image-box" v-for="(team, idx) in filteredTeams" :key="idx">
        <img :src="team.cover" alt="team" class="teams-image" />
        <div class="content">
          <p class="title">{{ team.title }}</p>
          <router-link
            class="btn"
            :to="`/data?team=${encodeURIComponent(team.title)}`"
            >View</router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from "vue";
import teamData from "@/data/teams.json";
import AnimatedLetters from "@/components/AnimatedLetters/AnimatedLetters.vue";

const letterClass = ref("text-animate");
const searchQuery = ref("");
const filteredTeams = ref([]);

onMounted(() => {
  setTimeout(() => {
    letterClass.value = "text-animate-hover";
  }, 3000);

  filteredTeams.value = teamData.teams;
});

watch(searchQuery, (val) => {
  filteredTeams.value = teamData.teams.filter((team) =>
    team.title.toLowerCase().includes(val.toLowerCase())
  );
});
</script>

<style scoped src="./index.scss"></style>
