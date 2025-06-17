<script setup>
import Sidebar from "./components/layout/Sidebar.vue";
</script>

<template>
  <Sidebar />
  <router-view />
</template>

<style>
/* #app {
  color: #2c3e50;
} */

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body {
  margin: 0;
  font-family: 300 11px/1.4 "Red Hat Display", "sans-serif";
  color: #fff;
  background: #f9f9f9;
  display: block;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

body::-webkit-scrollbar {
  width: 10px;
}

body::-webkit-scrollbar-thumb {
  background: #bab9b9;
  border-radius: 5px;
}
</style>
