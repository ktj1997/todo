package hexagonal.todo.ports.in.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateTodoCommand {

  private Long id;
  private String name;
  private boolean checked;
  private int priority;

}