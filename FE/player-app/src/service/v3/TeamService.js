import axiosInstance from "../axiosInstance";

const API = "/api/v3/teams";

// جلب جميع الفرق
export const getAllTeams = () => {
  return axiosInstance.get(`${API}`).then((res) => res.data);
};

// جلب فريق حسب ID
export const getTeamById = (id) => {
  return axiosInstance.get(`${API}/${id}`).then((res) => res.data);
};

// إنشاء فريق جديد
export const createTeam = (data) => {
  return axiosInstance.post(`${API}`, data).then((res) => res.data);
};

// تحديث فريق
export const updateTeam = (id, data) => {
  return axiosInstance.put(`${API}/${id}`, data).then((res) => res.data);
};

// حذف فريق
export const deleteTeam = (id) => {
  return axiosInstance.delete(`${API}/${id}`);
};
