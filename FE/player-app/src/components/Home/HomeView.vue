<template>
  <div class="container home-page">
    <div class="text-zone">
      <h1>
        <img src="@/assets/logo.svg" alt="PremierZone" />
        <br />
        <AnimatedLetters
          :letterClass="letterClass"
          :strArray="'Welcome to'.split('')"
          :idx="12"
        />
        <br />
        <AnimatedLetters
          :letterClass="letterClass"
          :strArray="'Premier Zone Fantasy!'.split('')"
          :idx="15"
        />
      </h1>
      <h2>Your home for everything Premier League related!</h2>
      <router-link to="/teams" class="flat-button">GET STARTED</router-link>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import AnimatedLetters from "@/components/AnimatedLetters/AnimatedLetters.vue";

const letterClass = ref("text-animate");
let timer = null;

onMounted(() => {
  timer = setTimeout(() => {
    letterClass.value = "text-animate-hover";
  }, 4000);
});

onBeforeUnmount(() => {
  if (timer) clearTimeout(timer);
});
</script>

<style scoped src="./index.scss"></style>
