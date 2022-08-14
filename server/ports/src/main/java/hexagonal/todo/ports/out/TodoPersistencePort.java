package hexagonal.todo.ports.out;

import hexagonal.todo.ports.out.model.TodoPersistenceDto;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoPersistencePort {

  TodoPersistenceDto findTodoById(Long id);

  List<TodoPersistenceDto> findTodos(LocalDateTime start, LocalDateTime end);

  TodoPersistenceDto saveTodo(TodoPersistenceDto todoPersistenceDto);

  TodoPersistenceDto updateTodo(TodoPersistenceDto dto);

  void deleteTodo();

}
