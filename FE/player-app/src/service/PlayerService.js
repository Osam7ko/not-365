import axios from "axios";

const API_BASE = "http://localhost:8080/api/v2/football";

export const getPlayersByTeamAndSeason = async (teamId, season) => {
  const res = await axios.get(`${API_BASE}/players`, {
    params: { teamId, season },
  });
  return res.data;
};
