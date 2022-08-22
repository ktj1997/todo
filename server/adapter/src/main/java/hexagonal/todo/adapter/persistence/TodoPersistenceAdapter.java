package hexagonal.todo.adapter.persistence;


import hexagonal.todo.adapter.persistence.mapper.TodoEntityMapper;
import hexagonal.todo.adapter.persistence.model.TodoJpaEntity;
import hexagonal.todo.adapter.persistence.repository.TodoJpaRepository;
import hexagonal.todo.ports.out.TodoPersistencePort;
import hexagonal.todo.ports.out.model.TodoPersistenceDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class TodoPersistenceAdapter implements TodoPersistencePort {

  private final TodoEntityMapper todoEntityMapper;
  private final TodoJpaRepository todoJpaRepository;

  @Override
  public TodoPersistenceDto findTodoById(Long id) {
    TodoJpaEntity entity = todoJpaRepository.findById(id).orElseThrow(IllegalAccessError::new);
    return todoEntityMapper.entityToDto(entity);
  }

  @Override
  public List<TodoPersistenceDto> findTodos(LocalDateTime start, LocalDateTime end) {
    List<TodoJpaEntity> entities = todoJpaRepository.findAllByCreatedAtBetween(start, end);
    return entities.stream()
        .map(todoEntityMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @Override
  public TodoPersistenceDto saveTodo(TodoPersistenceDto dto) {
    TodoJpaEntity entity = todoEntityMapper.dtoToEntity(dto);
    todoJpaRepository.save(entity);

    return todoEntityMapper.entityToDto(entity);
  }

  @Override
  public TodoPersistenceDto updateTodo(Long id, TodoPersistenceDto dto) {
    TodoJpaEntity entity = todoJpaRepository.findById(id).orElseThrow(IllegalStateException::new);
    entity.update(
        dto.getName(),
        dto.isChecked(),
        dto.getPriority()
    );
    return todoEntityMapper.entityToDto(entity);
  }

  @Override
  public List<TodoPersistenceDto> updateTodos(Map<Long, TodoPersistenceDto> dtos) {
    Set<Long> ids = dtos.keySet();
    List<TodoJpaEntity> entities =
        todoJpaRepository.findAllByIdIn(ids)
            .stream().map(
                entity -> {
                  TodoPersistenceDto dto = dtos.get(entity.getId());
                  return entity.update(
                      dto.getName(),
                      dto.isChecked(),
                      dto.getPriority()
                  );
                }
            ).collect(Collectors.toList());

    return entities.stream().map(todoEntityMapper::entityToDto).collect(Collectors.toList());
  }

  @Override
  public void deleteTodo(Long id) {
    todoJpaRepository.deleteById(id);
  }
}
