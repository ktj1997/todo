import Todo from "../Todo";

const TodoList = (props) => {
  const { setTodos, todos } = props;
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
    <>
      {todos.map((todo, index) => (
        <Todo
          key={todo.id}
          index={index}
          data={todo}
          onChangeTodo={onChangeTodoHandler}
          onDeleteTodo={onDeleteTodoHandler}
        />
      ))}
    </>
  );
};

export default TodoList;
