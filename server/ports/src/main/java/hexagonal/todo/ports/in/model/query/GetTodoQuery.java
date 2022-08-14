package hexagonal.todo.ports.in.model.query;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTodoQuery {

  private LocalDate date;
}
