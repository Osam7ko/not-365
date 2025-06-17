import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080/",
});
// const instance = axios.create({
//   baseURL: process.env.VUE_APP_API_BASE || "http://localhost:8081/api",
// });

export default instance;
