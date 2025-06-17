<template>
  <div class="container position-page">
    <h1 class="page-title">
      <AnimatedLetters
        :letterClass="letterClass"
        :strArray="'Positions'.split('')"
        :idx="15"
      />
    </h1>

    <div class="search-bar">
      <input
        type="text"
        placeholder="Search for positions"
        v-model="searchQuery"
      />
    </div>

    <div class="images-container">
      <div
        class="image-box"
        v-for="(position, idx) in filteredPositions"
        :key="idx"
      >
        <img :src="position.cover" alt="position" class="teams-image" />
        <div class="content">
          <p class="title">{{ position.title }}</p>
          <router-link
            class="btn"
            :to="`/data?position=${encodeURIComponent(position.search)}`"
          >
            View
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import AnimatedLetters from "@/components/AnimatedLetters/AnimatedLetters.vue";
import positionData from "@/data/positions.json";

const letterClass = ref("text-animate");
const searchQuery = ref("");

onMounted(() => {
  setTimeout(() => {
    letterClass.value = "text-animate-hover";
  }, 3000);
});

const filteredPositions = computed(() => {
  return positionData.positions.filter((pos) =>
    pos.title.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
});
</script>

<style scoped src="./index.scss"></style>
