// src/service/TeamStatsService.js
import axios from "axios";

const API_BASE = "http://localhost:8080/api/v2/football/teams";

export const getTeamStats = async (leagueId, season) => {
  const res = await axios.get(`${API_BASE}/stats`, {
    params: { league: leagueId, season },
  });
  return res.data;
};
