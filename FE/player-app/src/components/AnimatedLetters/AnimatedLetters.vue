<template>
  <span>
    <span
      v-for="(char, i) in strArray"
      :key="`${char}-${i}`"
      :class="`${letterClass} _${i + idx}`"
    >
      {{ char }}
    </span>
  </span>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps({
  letterClass: String,
  strArray: Array,
  idx: Number,
});
</script>

<style scoped src="./index.scss"></style>
