package usa.bogdan.web.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import usa.bogdan.web.Entities.ToDoEntity;
import usa.bogdan.web.Entities.UserEntity;
import usa.bogdan.web.Repositories.Repository;
import usa.bogdan.web.Repositories.UserRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Repository toDoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserEntity registerUser(String userName, String password) {
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public UserEntity findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    public void create(ToDoEntity toDoEntity) {
        toDoRepository.save(toDoEntity);
    }

    public List<ToDoEntity> findAll() {
        return toDoRepository.findAll();
    }

    public void delete(ToDoEntity toDoEntity) {
        toDoRepository.delete(toDoEntity);
    }

    public ToDoEntity get(int id) {
        return toDoRepository.findById(id).orElse(null);
    }
}