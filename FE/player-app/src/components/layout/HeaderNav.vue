<template>
  <header class="main-header">
    <div class="logo">
      <router-link to="/">
        <img src="/logo.png" alt="Logo" />
      </router-link>
    </div>

    <button class="menu-toggle">
      <svg
        class="hamburger"
        :class="{ active: isMenuOpen }"
        viewBox="0 0 24 24"
      >
        <path class="line top" d="M3 6h18" />
        <path class="line middle" d="M3 12h18" />
        <path class="line bottom" d="M3 18h18" />
      </svg>
    </button>

    <div class="menu-overlay" :class="{ show: isMenuOpen }"></div>
    <nav
      class="nav-links"
      :class="{ show: isMenuOpen }"
      aria-hidden="!isMenuOpen"
    >
      <router-link to="/" class="text-logo">Home</router-link>
      <!-- <router-link to="/mapv1">Map</router-link> -->
      <!-- <router-link to="/mapv2">Map V2</router-link>
      <router-link to="/github">Github</router-link> -->
    </nav>
  </header>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
</script>

<style>
.main-header {
  display: flex;
  align-items: center;
  background: linear-gradient(135deg, #2c3e50 0%, #1a1a2e 100%);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 1000;
  margin-bottom: 1rem;
  gap: 50%;
}
.text-logo {
  color: #ffffff;
}

/* Mobile menu button */
.menu-toggle {
  display: none;
  background: none;
  border: none;
  cursor: pointer;
  padding: 0.75rem;
  z-index: 1001;
}

.hamburger {
  width: 24px;
  height: 24px;
  transition: transform 0.3s ease;
}

.hamburger .line {
  stroke: #f8f9fa;
  stroke-width: 2;
  stroke-linecap: round;
  transform-origin: center;
  transition: all 0.3s ease;
}

.hamburger.active .top {
  transform: translateY(6px) rotate(45deg);
}

.hamburger.active .middle {
  opacity: 0;
}

.hamburger.active .bottom {
  transform: translateY(-6px) rotate(-45deg);
}

.menu-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 999;
  opacity: 0;
  visibility: hidden;
  transition: all 0.3s ease;
}

.menu-overlay.show {
  opacity: 1;
  visibility: visible;
}

.logo img {
  width: 60px;
  transition: transform 0.3s ease;
}

.logo:hover img {
  transform: scale(1.05);
}

.nav-links {
  display: flex;
  gap: 1.5rem;
}

/* Mobile styles */
@media (max-width: 768px) {
  .main-header {
    flex-wrap: wrap;
    padding: 0.5rem 1rem;
    align-items: center;
    gap: 10px;
  }

  .menu-toggle {
    display: block;
    order: 1;
    flex-shrink: 0;
  }

  .logo {
    order: 0;
    flex-shrink: 0;
    width: 60px;
  }

  .auth-buttons {
    order: 2;
    margin-left: 0;
    flex-grow: 1;
    display: flex;
    justify-content: flex-end;
    gap: 10px;
  }

  .nav-links {
    display: none;
    flex-direction: column;
    gap: 0;
    order: 3;
    padding: 0.5rem 0;
  }

  .nav-links {
    position: fixed;
    top: 0;
    width: 80%;
    max-width: 300px;
    height: 40vh;
    background: #1a1a2e;
    box-shadow: -5px 0 15px rgba(0, 0, 0, 0.2);
    transition: right 0.3s ease;
    padding-top: 70px;
    z-index: 1000;
  }

  .nav-links.show {
    right: 0;
    display: flex;
  }

  .nav-links a {
    padding: 0.75rem 1rem;
    border-radius: 0;
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  }

  .auth-buttons {
    flex-direction: row;
    gap: 10px;
  }

  .btn,
  .logout-btn {
    width: auto;
    text-align: center;
    padding: 0.5rem 1rem;
  }
}

@media (max-width: 480px) {
  .logo img {
    width: 50px;
  }

  .main-header {
    padding: 0.5rem;
  }
}

.nav-links a {
  text-decoration: none;
  color: #f8f9fa;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  transition: all 0.3s ease;
  position: relative;
}

.nav-links a:hover {
  color: #ffffff;
}

.nav-links a::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  width: 0;
  height: 2px;
  background: #4cc9f0;
  transition: all 0.3s ease;
}

.nav-links a:hover::after {
  width: 100%;
  left: 0;
}

.auth-buttons {
  display: flex;
  gap: 1rem;
}

.btn {
  padding: 0.5rem 1.25rem;
  font-size: 0.875rem;
  text-decoration: none;
  border-radius: 0.25rem;
  transition: all 0.3s ease;
  font-weight: 500;
  display: inline-block;
  cursor: pointer;
}

.btn-outline {
  border: 1px solid #4cc9f0;
  color: #4cc9f0;
  background: transparent;
}

.btn-outline:hover {
  background-color: rgba(76, 201, 240, 0.1);
}

.btn-filled {
  background-color: #4cc9f0;
  color: #1a1a2e;
  border: none;
}

.btn-filled:hover {
  background-color: #3aa8d8;
  transform: translateY(-1px);
}

.logout-btn {
  color: #f8f9fa;
  background: transparent;
  border: 1px solid #f8f9fa;
  padding: 0.5rem 1.25rem;
  border-radius: 0.25rem;
  transition: all 0.3s ease;
  font-weight: 500;
}

.logout-btn:hover {
  background: rgba(248, 249, 250, 0.1);
  transform: translateY(-1px);
}

/* Active route styling */
.router-link-active {
  color: #4cc9f0 !important;
}

.router-link-active::after {
  width: 100% !important;
  left: 0 !important;
}
</style>
