import axiosInstance from "../axiosInstance";

const API = "/api/v3/seasons";

// جلب جميع المواسم
export const getAllSeasons = () => {
  return axiosInstance.get(`${API}`).then((res) => res.data);
};

// جلب موسم محدد
export const getSeasonById = (id) => {
  return axiosInstance.get(`${API}/${id}`).then((res) => res.data);
};

// إنشاء موسم جديد
export const createSeason = (data) => {
  return axiosInstance.post(`${API}`, data).then((res) => res.data);
};

// تعديل موسم
export const updateSeason = (id, data) => {
  return axiosInstance.put(`${API}/${id}`, data).then((res) => res.data);
};

// حذف موسم
export const deleteSeason = (id) => {
  return axiosInstance.delete(`${API}/${id}`);
};
