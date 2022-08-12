package hexagonal.todo.application;

import hexagonal.todo.ports.in.TodoUseCase;
import hexagonal.todo.ports.in.model.command.CreateTodoCommand;
import hexagonal.todo.ports.in.model.command.UpdateTodoCommand;
import hexagonal.todo.ports.in.model.query.GetTodoQuery;
import hexagonal.todo.ports.out.TodoPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService implements TodoUseCase {

  private final TodoPersistencePort todoPersistencePort;

  @Override
  public void getTodos(GetTodoQuery query) {

  }

  @Override
  public void createTodo(CreateTodoCommand command) {

  }

  @Override
  public void updateTodo(UpdateTodoCommand command) {

  }
}
