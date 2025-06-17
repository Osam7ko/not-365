<template>
  <div class="season-highlights">
    <div class="main-grid">
      <div class="table-wrapper">
        <h3>League Table</h3>
        <table>
          <thead>
            <tr>
              <th>#</th>
              <th>Team</th>
              <th>Points</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="team in displayedTeams" :key="team.position">
              <td>{{ team.position }}</td>
              <td>
                <img
                  :src="getLogo(team.teamName)"
                  alt="logo"
                  class="team-logo"
                />
                {{ team.teamName }}
              </td>
              <td>{{ team.points }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="side-tables">
        <div class="side-table">
          <h3>Top Scorers</h3>
          <table>
            <thead>
              <tr>
                <th>Player</th>
                <th>Team</th>
                <th>Goals</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="p in topScorers" :key="p.playerName">
                <td>{{ p.playerName }}</td>
                <td>
                  <img
                    :src="getLogo(p.teamName)"
                    alt="logo"
                    class="team-logo"
                  />
                  {{ p.teamName }}
                </td>
                <td>{{ p.goals }}</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="side-table">
          <h3>Top Assists</h3>
          <table>
            <thead>
              <tr>
                <th>Player</th>
                <th>Team</th>
                <th>Assists</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="p in topAssists" :key="p.playerName">
                <td>{{ p.playerName }}</td>
                <td>
                  <img
                    :src="getLogo(p.teamName)"
                    alt="logo"
                    class="team-logo"
                  />
                  {{ p.teamName }}
                </td>
                <td>{{ p.assists }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import teamsData from "@/data/teams.json";
import { computed, ref } from "vue";

const props = defineProps({
  season: Number,
  leagueTop: Array,
  topScorers: Array,
  topAssists: Array,
});

const showFullTable = ref(false);

// logos
const getLogo = (teamName) => {
  const team = teamsData.teams.find((t) => t.title === teamName);
  return team?.cover || "";
};

const displayedTeams = computed(() => {
  return showFullTable.value ? props.leagueTopFull : props.leagueTop;
});
</script>

<style scoped>
.season-highlights {
  padding: 30px;
  background-color: #f9f9f9;
  border-radius: 12px;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}
.main-grid {
  display: flex;
  gap: 30px;
  flex-wrap: wrap;
  align-items: flex-start;
}
.table-wrapper {
  flex: 2;
  background: #fff;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
}
.side-tables {
  display: flex;
  flex-direction: column;
  gap: 20px;
  flex: 1;
}
.side-table {
  background: #fff;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
}
table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
  margin-bottom: 15px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}
thead {
  background-color: #ffd700;
}
th,
td {
  padding: 12px 15px;
  text-align: left;
  color: #333;
}
th {
  font-weight: 700;
  font-size: 16px;
  border-bottom: 2px solid #e0c200;
}
td {
  font-size: 14px;
  border-bottom: 1px solid #eee;
}
tbody tr:nth-child(odd) {
  background-color: #fafafa;
}
tbody tr:hover {
  background-color: #fff3b0;
  cursor: default;
}
.team-logo {
  width: 24px;
  height: 24px;
  margin-right: 10px;
  vertical-align: middle;
  object-fit: contain;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  border-radius: 4px;
  padding: 2px;
}
button {
  padding: 8px 16px;
  border: none;
  background: #ffd700;
  cursor: pointer;
  font-weight: 700;
  border-radius: 6px;
  transition: background-color 0.3s ease;
  box-shadow: 0 2px 6px rgba(255, 215, 0, 0.6);
}
button:hover,
button:focus {
  background-color: #e6c200;
  outline: none;
  box-shadow: 0 0 8px rgba(230, 194, 0, 0.8);
}
h3 {
  color: #333;
  font-weight: 700;
  font-size: 20px;
  margin-bottom: 15px;
}
</style>
