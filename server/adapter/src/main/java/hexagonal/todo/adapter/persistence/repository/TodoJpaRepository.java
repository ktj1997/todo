package hexagonal.todo.adapter.persistence.repository;

import hexagonal.todo.adapter.persistence.model.TodoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<TodoJpaEntity,Long> {

}
