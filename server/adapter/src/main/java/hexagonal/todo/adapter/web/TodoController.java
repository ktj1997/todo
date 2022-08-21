package hexagonal.todo.adapter.web;


import hexagonal.todo.adapter.web.request.CreateTodoRequest;
import hexagonal.todo.adapter.web.request.UpdateTodoRequest;
import hexagonal.todo.adapter.web.response.CommonResponse;
import hexagonal.todo.ports.in.TodoUseCase;
import hexagonal.todo.ports.in.model.command.CreateTodoCommand;
import hexagonal.todo.ports.in.model.command.UpdateTodoCommand;
import hexagonal.todo.ports.in.model.info.TodoWebDto;
import hexagonal.todo.ports.in.model.query.GetTodoQuery;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

  private final TodoUseCase todoUseCase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<List<TodoWebDto>> getTodo(
      @RequestParam
      @DateTimeFormat(iso = ISO.DATE)
      LocalDate date
  ) {
    GetTodoQuery query = new GetTodoQuery(
        date
    );
    List<TodoWebDto> todos = todoUseCase.getTodos(query);
    return new CommonResponse<>(todos);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommonResponse<TodoWebDto> createTodo(
      @RequestBody
      CreateTodoRequest req
  ) {
    CreateTodoCommand command = new CreateTodoCommand(
        req.getName(),
        req.getPriority()
    );
    TodoWebDto dto = todoUseCase.createTodo(command);
    return new CommonResponse<>(dto);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<List<TodoWebDto>> updateTodo(
      @RequestParam
      @DateTimeFormat(iso = ISO.DATE)
      LocalDate date,
      @RequestBody UpdateTodoRequest req
  ) {
    UpdateTodoCommand command = new UpdateTodoCommand(
        req.getTodos()
    );

    List<TodoWebDto> dtos = todoUseCase.updateTodo(command);
    return new CommonResponse<>(dtos);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public CommonResponse<Void> deleteTodo(
      @PathVariable Long id
  ) {
    todoUseCase.deleteTodo(id);
    return new CommonResponse<>();
  }
}
