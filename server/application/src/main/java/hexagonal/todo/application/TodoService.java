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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService implements TodoUseCase {
  private final TodoPersistencePort todoPersistencePort;
  private final TodoModelMapper todoModelMapper;

  @Override
  public List<TodoWebDto> getTodos(GetTodoQuery query) {
    LocalDate targetDate = query.getDate();

    LocalDateTime startDate = LocalDateTime.of(targetDate, LocalTime.MIN);
    LocalDateTime endDate = LocalDateTime.of(targetDate, LocalTime.MAX);

    List<TodoPersistenceDto> persistenceDtos =
        todoPersistencePort.findTodos(startDate, endDate);

    return persistenceDtos.stream()
        .map(todoModelMapper::persistenceDtoToWebDto)
        .collect(Collectors.toList());
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
    log.info("In Service :{}", TransactionSynchronizationManager.getCurrentTransactionName());
    TodoPersistenceDto dto = todoPersistencePort.findTodoById(command.getId());
    Todo todo = todoModelMapper
        .dtoToModel(dto)
        .update(
            command.getName(),
            command.getPriority(),
            command.isChecked()
        );

    dto = todoPersistencePort.updateTodo(todoModelMapper.modelToPersistenceDto(todo));
    return todoModelMapper.persistenceDtoToWebDto(dto);
  }

  @Override
  public void deleteTodo(Long id) {
    todoPersistencePort.deleteTodo();
  }
}
