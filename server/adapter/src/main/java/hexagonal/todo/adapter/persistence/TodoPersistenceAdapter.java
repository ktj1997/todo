package hexagonal.todo.adapter.persistence;


import hexagonal.todo.adapter.persistence.mapper.TodoEntityMapper;
import hexagonal.todo.adapter.persistence.model.TodoJpaEntity;
import hexagonal.todo.adapter.persistence.repository.TodoJpaRepository;
import hexagonal.todo.ports.out.TodoPersistencePort;
import hexagonal.todo.ports.out.model.TodoDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoPersistenceAdapter implements TodoPersistencePort {

  private final TodoEntityMapper todoEntityMapper;
  private final TodoJpaRepository todoJpaRepository;

  @Override
  public TodoDto findTodoById(Long id) {
    TodoJpaEntity todo = todoJpaRepository.findById(id).orElseThrow(IllegalAccessError::new);
    return todoEntityMapper.entityToDto(todo);
  }

  @Override
  public List<TodoDto> findTodos() {
    return null;
  }

  @Override
  public TodoDto saveTodo() {
    return null;
  }

  @Override
  public TodoDto updateTodo(TodoDto dto) {
    TodoJpaEntity entity = todoJpaRepository.save(todoEntityMapper.dtoToEntity(dto));
    return todoEntityMapper.entityToDto(entity);
  }

  @Override
  public void deleteTodo() {

  }
}
