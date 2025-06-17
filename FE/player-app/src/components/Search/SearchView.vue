<template>
  <div class="container teams-page">
    <h1 class="page-title">
      <br />
      <br />
      <AnimatedLetters
        :letterClass="letterClass"
        :strArray="'Search'.split('')"
        :idx="15"
      />
    </h1>
    <div class="search-bar">
      <input
        type="text"
        placeholder="Search for players"
        v-model="searchQuery"
      />
      <button @click="goToPlayerSearch">Go</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import AnimatedLetters from "@/components/AnimatedLetters/AnimatedLetters.vue";
import { useRouter } from "vue-router";
const router = useRouter();

const letterClass = ref("text-animate");
const searchQuery = ref("");

let timer = null;

onMounted(() => {
  timer = setTimeout(() => {
    letterClass.value = "text-animate-hover";
  }, 3000);
});

onBeforeUnmount(() => {
  if (timer) clearTimeout(timer);
});

const goToPlayerSearch = () => {
  if (searchQuery.value.trim()) {
    router.push(`/data?name=${encodeURIComponent(searchQuery.value)}`);
  }
};
</script>

<style scoped src="./index.scss"></style>
