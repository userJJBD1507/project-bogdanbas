package usa.bogdan.web.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import usa.bogdan.web.Entities.ToDoEntity;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<ToDoEntity, Integer> {
    @Modifying
    @Transactional
    public void deleteByCurrentTask(String currentTask);

}