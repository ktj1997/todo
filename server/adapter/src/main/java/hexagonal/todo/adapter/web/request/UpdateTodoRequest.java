package hexagonal.todo.adapter.web.request;


import hexagonal.todo.ports.in.model.info.TodoWebDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateTodoRequest {
  List<TodoWebDto> todos;
}
