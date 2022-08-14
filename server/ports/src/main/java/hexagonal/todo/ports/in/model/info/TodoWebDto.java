package hexagonal.todo.ports.in.model.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoWebDto {

  private Long id;
  private String name;
  private boolean checked;
  private int priority;
}
