import axiosInstance from "./index";

export const fetchTodoList = (date) => {
  return axiosInstance.get(`/todos?date=${date}`);
};

export const saveTodo = (data) => {
  return axiosInstance.post(`/todos`, data);
};
