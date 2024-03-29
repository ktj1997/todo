package hexagonal.todo.adapter.persistence.model;

import hexagonal.todo.ports.out.model.TodoPersistenceDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "todo")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoJpaEntity extends BaseJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private boolean checked;

  @Column(nullable = false)
  private int priority;

  public TodoJpaEntity update(String name, boolean checked, int priority) {
    this.name = name;
    this.checked = checked;
    this.priority = priority;

    return this;
  }
}
