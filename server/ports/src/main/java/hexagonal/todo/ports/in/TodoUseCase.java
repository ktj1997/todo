package hexagonal.todo.ports.in;

import hexagonal.todo.ports.in.model.command.CreateTodoCommand;
import hexagonal.todo.ports.in.model.command.UpdateTodoCommand;
import hexagonal.todo.ports.in.model.info.TodoWebDto;
import hexagonal.todo.ports.in.model.query.GetTodoQuery;
import java.util.List;

public interface TodoUseCase {

  List<TodoWebDto> getTodos(GetTodoQuery query);

  TodoWebDto createTodo(CreateTodoCommand command);

  TodoWebDto updateTodo(Long id, UpdateTodoCommand command);

  List<TodoWebDto> updateTodos(UpdateTodoCommand command);

  void deleteTodo(Long id);
}
