import "./App.css";
import TodoList from "./components/TodoList";
import Form from "./components/Form";
import { useState, useEffect } from "react";
import { fetchTodoList } from "./libs/apis/TodoApi";
import { parseToDateFormat } from "./libs/utils/DateUtils";

const App = () => {
  const [todos, setTodos] = useState([]);

  useEffect(() => {
    fetchList();
  }, []);

  const fetchList = async () => {
    const date = parseToDateFormat(new Date());
    const response = await fetchTodoList(date);
    setTodos(response.data.data);
  };

  return (
    <div className="App">
      <div className="flex items-center justify-center h-screen bg-blue-50">
        <div className="w-full p-6 m-4 bg-white rounded shadow md:w-3/4 md:max-w-lg lg:w-3/5 lg:max-w-lg">
          <div className="flex justify-between mb-3">
            <h1>할 일 목록</h1>
          </div>
          <TodoList setTodos={setTodos} todos={todos} />
          <Form setTodos={setTodos} nextSequence={todos.length + 1} />
        </div>
      </div>
    </div>
  );
};

export default App;
