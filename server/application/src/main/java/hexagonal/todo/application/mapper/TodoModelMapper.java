package hexagonal.todo.application.mapper;

import hexagonal.todo.domain.Todo;
import hexagonal.todo.ports.in.model.info.TodoInfo;
import hexagonal.todo.ports.out.model.TodoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoModelMapper {
  Todo dtoToModel(TodoDto dto);
  TodoDto modelToPersistenceDto(Todo model);

  TodoInfo modelToWebDto(Todo model);
}
