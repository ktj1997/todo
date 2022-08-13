package hexagonal.todo.ports.out;

import hexagonal.todo.ports.out.model.TodoDto;
import java.util.List;

public interface TodoPersistencePort {

  TodoDto findTodoById(Long id);
  List<TodoDto> findTodos();
  TodoDto saveTodo();

  TodoDto updateTodo(TodoDto dto);
  void deleteTodo();

}
