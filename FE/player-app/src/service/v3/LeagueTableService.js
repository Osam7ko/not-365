import axiosInstance from "../axiosInstance";

const API = "/api/v3/league-table";

// جلب جميع الترتيبات
export const getAllLeagueTable = () => {
  return axiosInstance.get(`${API}`).then((res) => res.data);
};

// جلب الترتيب حسب ID
export const getLeagueTableById = (id) => {
  return axiosInstance.get(`${API}/${id}`).then((res) => res.data);
};

// جلب ترتيب الدوري حسب الموسم
export const getLeagueTableBySeason = (seasonId) => {
  return axiosInstance.get(`${API}/season/${seasonId}`).then((res) => res.data);
};

// إنشاء ترتيب
export const createLeagueTable = (data) => {
  return axiosInstance.post(`${API}`, data).then((res) => res.data);
};

// تحديث ترتيب
export const updateLeagueTable = (id, data) => {
  return axiosInstance.put(`${API}/${id}`, data).then((res) => res.data);
};

// حذف ترتيب
export const deleteLeagueTable = (id) => {
  return axiosInstance.delete(`${API}/${id}`);
};
