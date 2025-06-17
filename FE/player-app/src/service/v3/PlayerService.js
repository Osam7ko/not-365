import axiosInstance from "../axiosInstance";

const API = "/api/v3";

// جلب جميع اللاعبين
export const getAllPlayers = () => {
  return axiosInstance.get(`${API}/players`).then((res) => res.data);
};

// جلب لاعب حسب ID
export const getPlayerById = (id) => {
  return axiosInstance.get(`${API}/players/${id}`).then((res) => res.data);
};

// جلب أحدث إحصائيات للاعب
export const getPlayerWithLatestStats = (id) => {
  return axiosInstance
    .get(`${API}/players/${id}/latest-stats`)
    .then((res) => res.data);
};

// جلب لاعبين حسب teamId و seasonId
export const getPlayersByTeamAndSeason = (teamId, seasonId) => {
  return axiosInstance
    .get(`${API}/player-stats?teamId=${teamId}&seasonId=${seasonId}`)
    .then((res) => res.data);
};

// حذف لاعب
export const deletePlayer = (id) => {
  return axiosInstance.delete(`${API}/players/${id}`);
};

// إنشاء لاعب جديد
export const createPlayer = (data) => {
  return axiosInstance.post(`${API}/players`, data).then((res) => res.data);
};

// تحديث لاعب
export const updatePlayer = (id, data) => {
  return axiosInstance
    .put(`${API}/players/${id}`, data)
    .then((res) => res.data);
};
