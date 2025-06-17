import axiosInstance from "../axiosInstance";

const API = "/api/v3/team-seasons";

// جلب جميع روابط الفرق مع المواسم
export const getAllTeamSeasons = () => {
  return axiosInstance.get(`${API}`).then((res) => res.data);
};

// جلب رابط معين حسب ID
export const getTeamSeasonById = (id) => {
  return axiosInstance.get(`${API}/${id}`).then((res) => res.data);
};

// جلب جميع الفرق المشاركة في موسم معين
export const getTeamSeasonsBySeason = (seasonId) => {
  return axiosInstance.get(`${API}/season/${seasonId}`).then((res) => res.data);
};

// إنشاء رابط جديد لفريق مع موسم
export const createTeamSeason = (data) => {
  return axiosInstance.post(`${API}`, data).then((res) => res.data);
};

// تعديل رابط فريق مع موسم
export const updateTeamSeason = (id, data) => {
  return axiosInstance.put(`${API}/${id}`, data).then((res) => res.data);
};

// حذف الرابط
export const deleteTeamSeason = (id) => {
  return axiosInstance.delete(`${API}/${id}`);
};
