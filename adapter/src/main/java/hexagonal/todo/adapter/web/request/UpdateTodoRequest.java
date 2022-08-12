package hexagonal.todo.adapter.web.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateTodoRequest {

  private String name;
  private int priority;
  private boolean isFinished;
}
