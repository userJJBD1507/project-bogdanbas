package usa.bogdan.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usa.bogdan.web.Entities.ToDoEntity;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<ToDoEntity, Integer> {
}