package hexagonal.todo.adapter.persistence.repository;

import hexagonal.todo.adapter.persistence.model.TodoJpaEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<TodoJpaEntity, Long> {

  List<TodoJpaEntity> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
