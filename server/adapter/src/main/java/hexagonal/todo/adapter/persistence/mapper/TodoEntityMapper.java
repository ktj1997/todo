package hexagonal.todo.adapter.persistence.mapper;

import hexagonal.todo.adapter.persistence.model.TodoJpaEntity;
import hexagonal.todo.ports.out.model.TodoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoEntityMapper {
  TodoJpaEntity dtoToEntity(TodoDto dto);

  TodoDto entityToDto(TodoJpaEntity jpaEntity);
}
