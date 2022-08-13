import "./App.css";
import Todo from "./Todo";
import { useState } from "react";

const App = () => {
  const [todos, setTodos] = useState([
    {
      id: 1,
      name: "공부하기",
      isChecked: true
    },
    {
      id: 2,
      name: "운동하기",
      isChecked: false
    }
  ]);
  const onChangeTodoHandler = (index, newTodo) => {
    setTodos((prevState) => {
      let newTodos = [...prevState];
      newTodos[index] = newTodo;
      return newTodos;
    });
  };
  const onDeleteTodoHandler = (index) => {
    setTodos((prevState) => {
      const newTodos = [...prevState];
      newTodos.splice(index, 1);

      return newTodos;
    });
  };
  return (
    <div className="App">
      <div className="Container">
        <div className="TodoBlock">
          <div className="Title">
            <h1>할 일 목록</h1>
          </div>
          {todos.map((todo, index) => (
            <Todo
              key={todo.id}
              index={index}
              data={todo}
              onChangeTodo={onChangeTodoHandler}
              onDeleteTodo={onDeleteTodoHandler}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default App;
