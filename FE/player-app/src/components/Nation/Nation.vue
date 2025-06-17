<template>
  <div class="container nation-page">
    <h1 class="page-title">
      <AnimatedLetters
        :letterClass="letterClass"
        :strArray="'Nations'.split('')"
        :idx="15"
      />
    </h1>

    <div class="search-bar">
      <input
        type="text"
        placeholder="Search for countries"
        v-model="searchQuery"
      />
    </div>

    <div class="images-container">
      <div
        class="image-box"
        v-for="(nation, idx) in filteredNations"
        :key="idx"
      >
        <img
          :src="`https://flagcdn.com/w320/${nation.code.toLowerCase()}.png`"
          :alt="nation.name"
          class="flag-img"
        />
        <div class="content">
          <p class="title">{{ nation.name }}</p>
          <router-link
            class="btn"
            :to="`/data?nation=${encodeURIComponent(nation.search)}`"
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
import nationData from "@/data/nations.json";

const letterClass = ref("text-animate");
const searchQuery = ref("");

onMounted(() => {
  setTimeout(() => {
    letterClass.value = "text-animate-hover";
  }, 3000);
});

const filteredNations = computed(() => {
  return nationData.nations.filter((n) =>
    n.name.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
});
</script>

<style scoped src="./index.scss"></style>
