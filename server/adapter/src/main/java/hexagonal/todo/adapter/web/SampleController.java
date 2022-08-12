package hexagonal.todo.adapter.web;


import hexagonal.todo.adapter.web.request.CreateTodoRequest;
import hexagonal.todo.adapter.web.request.UpdateTodoRequest;
import hexagonal.todo.adapter.web.response.CommonResponse;
import hexagonal.todo.ports.in.TodoUseCase;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
public class SampleController {

  private final TodoUseCase todoUseCase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<Void> getTodo(
      @RequestParam
      @DateTimeFormat(pattern = "yyyy-mm-dd")
      LocalDate date
  ) {
    return new CommonResponse<>(null);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommonResponse<Void> createTodo(
      @RequestBody
      CreateTodoRequest req
  ) {

    return new CommonResponse<>(null);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CommonResponse<Void> updateTodo(
      @PathVariable Long id,
      @RequestBody UpdateTodoRequest req
  ) {
    return new CommonResponse<>(null);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public CommonResponse<Void> deleteTodo(
      @PathVariable Long id
  ) {
    return new CommonResponse<>(null);
  }
}
