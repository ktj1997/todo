package hexagonal.todo.adapter.web.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommonResponse<T> {

  public CommonResponse() {
    this.timeStamp = LocalDateTime.now();
  }

  public CommonResponse(T data) {
    this.data = data;
    this.timeStamp = LocalDateTime.now();
  }

  private T data;
  private LocalDateTime timeStamp;
}
