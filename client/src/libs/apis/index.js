import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: false,
  params: {
    language: "ko-KR"
  }
});

export default axiosInstance;
