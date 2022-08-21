package hexagonal.todo.application.mapper;

import hexagonal.todo.domain.Todo;
import hexagonal.todo.ports.in.model.info.TodoWebDto;
import hexagonal.todo.ports.out.model.TodoPersistenceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoModelMapper {

  Todo persistenceDtoToModel(TodoPersistenceDto dto);

  Todo webDtoToModel(TodoWebDto dto);
  TodoPersistenceDto modelToPersistenceDto(Todo model);

  TodoWebDto modelToWebDto(Todo model);

  TodoWebDto persistenceDtoToWebDto(TodoPersistenceDto dto);
}
