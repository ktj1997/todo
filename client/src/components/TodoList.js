import { DragDropContext, Draggable, Droppable } from "react-beautiful-dnd";
import Todo from "./Todo";

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
    <DragDropContext>
      <Droppable droppableId="TodoList">
        {(provided, snapshot) => (
          <div {...provided.droppableProps} ref={provided.innerRef}>
            {todos.map((todo, index) => (
              <Draggable
                key={String(todo.id)}
                draggableId={String(todo.id)}
                index={index}
              >
                {(provided, snapshot) => (
                  <div
                    ref={provided.innerRef}
                    {...provided.draggableProps}
                    {...provided.dragHandleProps}
                  >
                    <Todo
                      index={index}
                      data={todo}
                      onChangeTodo={onChangeTodoHandler}
                      onDeleteTodo={onDeleteTodoHandler}
                    />
                  </div>
                )}
              </Draggable>
            ))}
          </div>
        )}
      </Droppable>
    </DragDropContext>
  );
};

export default TodoList;
