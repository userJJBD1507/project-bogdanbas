package usa.bogdan.web.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import usa.bogdan.web.Entities.ToDoEntity;
import usa.bogdan.web.Models;
import usa.bogdan.web.Repositories.Repository;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repository repository;
    ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "updateTopic", groupId = "all")
    public void update(String message) throws Exception {
        ToDoEntity toDoEntity = objectMapper.readValue(message, ToDoEntity.class);
        repository.save(toDoEntity);
        System.out.println("Updated: " + toDoEntity.getCurrentTask());
    }
}
