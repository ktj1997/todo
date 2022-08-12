package hexagonal.todo.adapter.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTodoRequest {

  private String name;
  private int priority;
}
