package hexagonal.todo.ports.out.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoDto {

  private Long id;
  private String name;
  private boolean checked;
  private int priority;

}
