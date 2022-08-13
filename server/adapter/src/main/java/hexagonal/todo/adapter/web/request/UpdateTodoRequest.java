package hexagonal.todo.adapter.web.request;


import hexagonal.todo.ports.in.model.command.UpdateTodoCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.sql.Update;

@Getter
@AllArgsConstructor
public class UpdateTodoRequest {

  private String name;
  private int priority;
  private boolean checked;
}
