import axiosInstance from "../axiosInstance";

const API = "/api/v3/league-best-players";

// 📌 إنشاء أفضل لاعب في الدوري
export const createLeagueBestPlayer = (data) => {
  return axiosInstance.post(`${API}`, data).then((res) => res.data);
};

// 📌 جلب أفضل لاعبين في الدوري حسب الموسم
export const getLeagueBestPlayersBySeason = (seasonId) => {
  return axiosInstance.get(`${API}/season/${seasonId}`).then((res) => res.data);
};

// 📌 حذف أفضل لاعبين في الدوري حسب الموسم
export const deleteLeagueBestPlayersBySeason = (seasonId) => {
  return axiosInstance.delete(`${API}/season/${seasonId}`);
};
