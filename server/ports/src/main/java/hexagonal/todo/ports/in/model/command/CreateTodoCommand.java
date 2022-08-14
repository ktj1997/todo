package hexagonal.todo.ports.in.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTodoCommand {

  private String name;
  private int priority;
}
