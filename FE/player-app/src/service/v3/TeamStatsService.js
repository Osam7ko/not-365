import axiosInstance from "../axiosInstance";

const API = "/api/v3";

export const getTopScorersBySeason = (seasonId, limit = 10) => {
  return axiosInstance
    .get(`${API}/player-stats/top-scorers/${seasonId}?limit=${limit}`)
    .then((res) => res.data);
};

export const getTopAssistsBySeason = (seasonId, limit = 10) => {
  return axiosInstance
    .get(`${API}/player-stats/top-assists/${seasonId}?limit=${limit}`)
    .then((res) => res.data);
};

export const getLeagueTableBySeason = (seasonId) => {
  return axiosInstance
    .get(`${API}/league-table/season/${seasonId}`)
    .then((res) => res.data);
};

export const getTeamGoalsBySeason = (seasonId) => {
  return axiosInstance
    .get(`${API}/team-goals/season/${seasonId}`)
    .then((res) => res.data);
};
