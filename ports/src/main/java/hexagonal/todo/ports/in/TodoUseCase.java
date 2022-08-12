package hexagonal.todo.ports.in;

import hexagonal.todo.ports.in.model.command.CreateTodoCommand;
import hexagonal.todo.ports.in.model.command.UpdateTodoCommand;
import hexagonal.todo.ports.in.model.query.GetTodoQuery;

public interface TodoUseCase {

  void getTodos(GetTodoQuery query);

  void createTodo(CreateTodoCommand command);

  void updateTodo(UpdateTodoCommand command);
}
