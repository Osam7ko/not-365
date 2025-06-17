import axiosInstance from "../axiosInstance";

const API = "/api/v3/team-best-players";

// جلب أفضل اللاعبين لفريق حسب الموسم
export const getBestPlayersByTeam = (teamId) => {
  return axiosInstance.get(`${API}/team/${teamId}`).then((res) => res.data);
};

// جلب كل أفضل اللاعبين لموسم معيّن
export const getBestPlayersBySeason = (seasonId) => {
  return axiosInstance.get(`${API}/season/${seasonId}`).then((res) => res.data);
};
