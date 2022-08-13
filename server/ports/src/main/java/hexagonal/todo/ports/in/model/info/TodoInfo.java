package hexagonal.todo.ports.in.model.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoInfo {

  private Long id;
  private String name;
  private boolean checked;
  private int priority;
}
