import axiosInstance from "../axiosInstance";

const API = "/api/v3/player-stats";

// جلب جميع الإحصائيات
export const getAllPlayerStats = () => {
  return axiosInstance.get(`${API}`).then((res) => res.data);
};

// جلب إحصائية معينة حسب ID
export const getPlayerStatsById = (id) => {
  return axiosInstance.get(`${API}/${id}`).then((res) => res.data);
};

// جلب إحصائيات لاعب معين
export const getStatsByPlayer = (playerId) => {
  return axiosInstance.get(`${API}/player/${playerId}`).then((res) => res.data);
};

// جلب أحدث إحصائيات للاعب معين
export const getLatestStatsForPlayer = (playerId) => {
  return axiosInstance
    .get(`${API}/player/${playerId}/latest`)
    .then((res) => res.data);
};

// جلب الإحصائيات حسب الموسم
export const getStatsBySeason = (seasonId) => {
  return axiosInstance.get(`${API}/season/${seasonId}`).then((res) => res.data);
};

// جلب أعلى الهدافين
export const getTopScorers = (limit = 10) => {
  return axiosInstance
    .get(`${API}/top-scorers?limit=${limit}`)
    .then((res) => res.data);
};

// جلب أعلى صنّاع اللعب
export const getTopAssisters = (limit = 10) => {
  return axiosInstance
    .get(`${API}/top-assists?limit=${limit}`)
    .then((res) => res.data);
};

// إنشاء إحصائية جديدة
export const createPlayerStats = (data) => {
  return axiosInstance.post(`${API}`, data).then((res) => res.data);
};

// تعديل الإحصائية
export const updatePlayerStats = (id, data) => {
  return axiosInstance.put(`${API}/${id}`, data).then((res) => res.data);
};

// حذف إحصائية
export const deletePlayerStats = (id) => {
  return axiosInstance.delete(`${API}/${id}`);
};
