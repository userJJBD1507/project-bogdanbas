package usa.bogdan.web.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import usa.bogdan.web.Entities.ToDoEntity;
import usa.bogdan.web.Models;
import usa.bogdan.web.Repositories.Repository;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "deleteTopic", groupId = "all")
    public void delete(String message) throws Exception {
        ToDoEntity toDoEntity = objectMapper.readValue(message, ToDoEntity.class);
        repository.deleteByCurrentTask(toDoEntity.getCurrentTask());
        System.out.println("Deleted: " + toDoEntity.getCurrentTask());
    }
}
