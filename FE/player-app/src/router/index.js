import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/components/Home/HomeView.vue";
import TeamView from "@/components/Team/Teams.vue";
import TeamData from "@/components/TeamData/TeamData.vue";
import SearchView from "@/components/Search/SearchView.vue";
import Position from "@/components/Position/Position.vue";
import NationView from "@/components/Nation/Nation.vue";
import TeamV2View from "@/components/Team/TeamsV2.vue";
import TeamDataV2 from "@/components/TeamData/TeamDataV2.vue";
import TeamV3View from "@/components/Team/TeamsV3.vue";

const routes = [
  {
    name: "HomeView",
    component: HomeView,
    path: "/",
  },
  {
    name: "SearchView",
    component: SearchView,
    path: "/search",
  },
  {
    name: "TeamData",
    component: TeamData,
    path: "/data",
  },
  {
    name: "TeamDataV2",
    component: TeamDataV2,
    path: "/data-v2",
  },
  {
    name: "Position",
    component: Position,
    path: "/position",
  },
  {
    name: "TeamView",
    component: TeamView,
    path: "/teams",
  },
  {
    name: "TeamV2View",
    component: TeamV2View,
    path: "/teams-v2",
  },
  {
    name: "TeamV3View",
    component: TeamV3View,
    path: "/teams-v3",
  },
  {
    name: "NationView",
    component: NationView,
    path: "/nation",
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
