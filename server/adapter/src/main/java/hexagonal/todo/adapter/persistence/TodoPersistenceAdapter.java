package hexagonal.todo.adapter.persistence;


import hexagonal.todo.adapter.persistence.mapper.TodoEntityMapper;
import hexagonal.todo.adapter.persistence.model.TodoJpaEntity;
import hexagonal.todo.adapter.persistence.repository.TodoJpaRepository;
import hexagonal.todo.ports.out.TodoPersistencePort;
import hexagonal.todo.ports.out.model.TodoPersistenceDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class TodoPersistenceAdapter implements TodoPersistencePort {

  private final TodoEntityMapper todoEntityMapper;
  private final TodoJpaRepository todoJpaRepository;

  private final EntityManager em;

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
  public List<TodoPersistenceDto> updateTodo(List<TodoPersistenceDto> dtos) {
    List<TodoJpaEntity> entities = dtos.stream().map(todoEntityMapper::dtoToEntity).collect(Collectors.toList());
    todoJpaRepository.saveAll(entities);
    return entities.stream().map(todoEntityMapper::entityToDto).collect(Collectors.toList());
  }

  @Override
  public void deleteTodo(Long id) {
    todoJpaRepository.deleteById(id);
  }
}
