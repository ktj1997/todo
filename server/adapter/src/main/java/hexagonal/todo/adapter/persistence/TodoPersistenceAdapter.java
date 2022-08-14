package hexagonal.todo.adapter.persistence;


import hexagonal.todo.adapter.persistence.mapper.TodoEntityMapper;
import hexagonal.todo.adapter.persistence.model.TodoJpaEntity;
import hexagonal.todo.adapter.persistence.repository.TodoJpaRepository;
import hexagonal.todo.ports.out.TodoPersistencePort;
import hexagonal.todo.ports.out.model.TodoPersistenceDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoPersistenceAdapter implements TodoPersistencePort {

  private final TodoEntityMapper todoEntityMapper;
  private final TodoJpaRepository todoJpaRepository;

  @Override
  public TodoPersistenceDto findTodoById(Long id) {
    TodoJpaEntity todo = todoJpaRepository.findById(id).orElseThrow(IllegalAccessError::new);
    return todoEntityMapper.entityToDto(todo);
  }

  @Override
  public List<TodoPersistenceDto> findTodos() {
    return null;
  }

  @Override
  public TodoPersistenceDto saveTodo(TodoPersistenceDto dto) {
    TodoJpaEntity entity = todoEntityMapper.dtoToEntity(dto);
    todoJpaRepository.save(entity);

    return todoEntityMapper.entityToDto(entity);
  }

  @Override
  public TodoPersistenceDto updateTodo(TodoPersistenceDto dto) {
    TodoJpaEntity entity = todoEntityMapper.dtoToEntity(dto);
    return todoEntityMapper.entityToDto(entity);
  }

  @Override
  public void deleteTodo() {

  }
}
