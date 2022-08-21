package hexagonal.todo.ports.in.model.command;

import hexagonal.todo.ports.in.model.info.TodoWebDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateTodoCommand {
  List<TodoWebDto> todos;
}
