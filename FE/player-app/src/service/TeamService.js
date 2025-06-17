import axios from "axios";

const API_BASE = "http://localhost:8080/api/v2/football";

export const getTeamsByLeagueAndSeason = async (leagueId, season) => {
  const response = await axios.get(`${API_BASE}/teams`, {
    params: { league: leagueId, season: season },
  });
  return response.data;
};
