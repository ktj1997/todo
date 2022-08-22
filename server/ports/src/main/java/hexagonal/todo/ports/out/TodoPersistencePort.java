package hexagonal.todo.ports.out;

import hexagonal.todo.ports.out.model.TodoPersistenceDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TodoPersistencePort {

  TodoPersistenceDto findTodoById(Long id);

  List<TodoPersistenceDto> findTodos(LocalDateTime start, LocalDateTime end);

  TodoPersistenceDto saveTodo(TodoPersistenceDto todoPersistenceDto);

  TodoPersistenceDto updateTodo(Long id, TodoPersistenceDto dto);

  List<TodoPersistenceDto> updateTodos(Map<Long, TodoPersistenceDto> dtos);

  void deleteTodo(Long id);
}
