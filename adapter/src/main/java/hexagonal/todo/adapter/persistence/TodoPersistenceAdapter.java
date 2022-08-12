package hexagonal.todo.adapter.persistence;


import hexagonal.todo.ports.out.TodoPersistencePort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TodoPersistenceAdapter implements TodoPersistencePort {

}
