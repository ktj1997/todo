package hexagonal.todo.application;

import hexagonal.todo.application.mapper.TodoModelMapper;
import hexagonal.todo.domain.Todo;
import hexagonal.todo.ports.in.TodoUseCase;
import hexagonal.todo.ports.in.model.command.CreateTodoCommand;
import hexagonal.todo.ports.in.model.command.UpdateTodoCommand;
import hexagonal.todo.ports.in.model.info.TodoWebDto;
import hexagonal.todo.ports.in.model.query.GetTodoQuery;
import hexagonal.todo.ports.out.TodoPersistencePort;
import hexagonal.todo.ports.out.model.TodoPersistenceDto;
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
  public TodoWebDto createTodo(CreateTodoCommand command) {
    TodoPersistenceDto persistenceDto = todoPersistencePort.saveTodo(
        new TodoPersistenceDto(
            null,
            command.getName(),
            false,
            command.getPriority()
        )
    );

    return todoModelMapper.persistenceDtoToWebDto(persistenceDto);
  }

  @Override
  public TodoWebDto updateTodo(UpdateTodoCommand command) {
    TodoPersistenceDto dto = todoPersistencePort.findTodoById(command.getId());
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
