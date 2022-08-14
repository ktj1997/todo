package hexagonal.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Todo {

  private String name;
  private int priority;
  private boolean checked;

  public Todo update(String name, int priority, boolean checked) {
    this.name = name;
    this.priority = priority;
    this.checked = checked;

    return this;
  }
}
