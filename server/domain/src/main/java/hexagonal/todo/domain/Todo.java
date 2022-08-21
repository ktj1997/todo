package hexagonal.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Todo {

  private Long id;
  private String name;
  private int priority;
  private boolean checked;
}
