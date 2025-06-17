<template>
  <div class="nav-bar">
    <router-link class="logo" to="/">
      <img src="/logo.png" alt="Logo" />
    </router-link>

    <nav :class="{ 'mobile-show': showNav }">
      <router-link to="/" @click="closeNav">
        <font-awesome-icon :icon="['fas', 'home']" />
      </router-link>
      <!-- <router-link to="/teams" class="teams-link" @click="closeNav">
        <font-awesome-icon :icon="['fas', 'users']" />
      </router-link> -->
      <router-link to="/teams-v2" class="teams-link" @click="closeNav">
        <font-awesome-icon :icon="['fas', 'users']" />V2
      </router-link>
      <router-link to="/teams-v3" class="teams-link" @click="closeNav">
        <font-awesome-icon :icon="['fas', 'users']" />V3
      </router-link>
      <router-link to="/nation" class="nation-link" @click="closeNav">
        <font-awesome-icon :icon="['fas', 'flag']" />
      </router-link>
      <router-link to="/position" class="position-link" @click="closeNav">
        <font-awesome-icon :icon="['fas', 'tshirt']" />
      </router-link>
      <router-link to="/search" class="search-link" @click="closeNav">
        <font-awesome-icon :icon="['fas', 'search']" />
      </router-link>
      <font-awesome-icon
        icon="fa-solid fa-xmark"
        class="close-icon"
        size="3x"
        @click="closeNav"
      />
    </nav>

    <font-awesome-icon
      icon="fa-solid fa-bars"
      class="hamburger-icon"
      size="3x"
      @click="showNav = true"
    />
  </div>
</template>

<script setup>
import { ref } from "vue";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faHome,
  faUsers,
  faFlag,
  faTshirt,
  faSearch,
  faBars,
  faXmark,
} from "@fortawesome/free-solid-svg-icons";

library.add(faHome, faUsers, faFlag, faTshirt, faSearch, faBars, faXmark);

const showNav = ref(false);

const closeNav = () => {
  showNav.value = false;
};
</script>

<style scoped src="./index.scss"></style>
