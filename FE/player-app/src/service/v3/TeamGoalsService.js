import axiosInstance from "../axiosInstance";

const API = "/api/v3/team-goals";

// جلب جميع سجلات الأهداف لكل الفرق
export const getAllTeamGoals = () => {
  return axiosInstance.get(`${API}`).then((res) => res.data);
};

// جلب سجل أهداف فريق معين حسب ID
export const getTeamGoalsById = (id) => {
  return axiosInstance.get(`${API}/${id}`).then((res) => res.data);
};

// جلب أهداف الفرق حسب الموسم
export const getTeamGoalsBySeason = (seasonId) => {
  return axiosInstance.get(`${API}/season/${seasonId}`).then((res) => res.data);
};

// إنشاء سجل أهداف لفريق
export const createTeamGoals = (data) => {
  return axiosInstance.post(`${API}`, data).then((res) => res.data);
};

// تعديل سجل الأهداف
export const updateTeamGoals = (id, data) => {
  return axiosInstance.put(`${API}/${id}`, data).then((res) => res.data);
};

// حذف سجل أهداف
export const deleteTeamGoals = (id) => {
  return axiosInstance.delete(`${API}/${id}`);
};
