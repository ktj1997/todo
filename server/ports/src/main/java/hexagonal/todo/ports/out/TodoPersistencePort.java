package hexagonal.todo.ports.out;

import hexagonal.todo.ports.out.model.TodoPersistenceDto;
import java.util.List;

public interface TodoPersistencePort {

  TodoPersistenceDto findTodoById(Long id);
  List<TodoPersistenceDto> findTodos();
  TodoPersistenceDto saveTodo(TodoPersistenceDto todoPersistenceDto);

  TodoPersistenceDto updateTodo(TodoPersistenceDto dto);
  void deleteTodo();

}
