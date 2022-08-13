package hexagonal.todo.application;

import hexagonal.todo.application.mapper.TodoModelMapper;
import hexagonal.todo.domain.Todo;
import hexagonal.todo.ports.in.TodoUseCase;
import hexagonal.todo.ports.in.model.command.CreateTodoCommand;
import hexagonal.todo.ports.in.model.command.UpdateTodoCommand;
import hexagonal.todo.ports.in.model.info.TodoInfo;
import hexagonal.todo.ports.in.model.query.GetTodoQuery;
import hexagonal.todo.ports.out.TodoPersistencePort;
import hexagonal.todo.ports.out.model.TodoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService implements TodoUseCase {

  private final TodoPersistencePort todoPersistencePort;
  private final TodoModelMapper todoModelMapper;

  @Override
  public void getTodos(GetTodoQuery query) {
    todoPersistencePort.findTodos();
  }

  @Override
  public void createTodo(CreateTodoCommand command) {
    todoPersistencePort.saveTodo();
  }

  @Override
  public TodoInfo updateTodo(UpdateTodoCommand command) {
    TodoDto dto = todoPersistencePort.findTodoById(command.getId());
    Todo todo = todoModelMapper
        .dtoToModel(dto)
        .update(
            command.getName(),
            command.getPriority(),
            command.isChecked()
        );

    todoPersistencePort.updateTodo(todoModelMapper.modelToPersistenceDto(todo));
    return todoModelMapper.modelToWebDto(todo);
  }

  @Override
  public void deleteTodo(Long id) {
    todoPersistencePort.deleteTodo();
  }
}
