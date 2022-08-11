package hexagonal.todo.adapter.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/helllo")
    public String get(){
      return "hello";
    }
}
