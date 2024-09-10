package usa.bogdan.web.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import usa.bogdan.web.Entities.ToDoEntity;
import usa.bogdan.web.Entities.UserEntity;

//@Service
//public class KafkaService {
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    @Autowired
//    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//    public void create(String res) {
//        kafkaTemplate.send("updateTopic", res);
//    }
//    public void delete(String res) {
//        System.out.println("DELETED");
//        kafkaTemplate.send("deleteTopic", res);
//    }
//}



@Service
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void create(ToDoEntity toDoEntity) {
        try {
            String json = new ObjectMapper().writeValueAsString(toDoEntity);
            kafkaTemplate.send("updateTopic", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(ToDoEntity toDoEntity) {
        try {
            String json = new ObjectMapper().writeValueAsString(toDoEntity);
            kafkaTemplate.send("deleteTopic", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
