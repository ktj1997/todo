import axiosInstance from "./index";

export const fetchTodoList = async (date) => {
  return axiosInstance.get(`/todos?date=${date}`);
};

export const saveTodo = async (data) => {
  return await axiosInstance.post(`/todos`, data);
};
