package usa.bogdan.web.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usa.bogdan.web.Entities.ToDoEntity;

import usa.bogdan.web.Entities.UserEntity;
import usa.bogdan.web.Repositories.UserRepository;
import usa.bogdan.web.Services.KafkaService;
import usa.bogdan.web.Services.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Service appService;
    @Autowired
    private KafkaService kafkaService;
    ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/")
    public String todoList(Model model) {
        model.addAttribute("tasks", appService.findAll());
        return "todo";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String userName,
                               @RequestParam("password") String password) {
        appService.registerUser(userName, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/todo")
    public String createTasks(@RequestParam("task") String taskName, Model model) {
        ToDoEntity toDoEntity = new ToDoEntity(taskName);
        appService.create(toDoEntity);
        model.addAttribute("tasks", appService.findAll());
        return "todo";
    }
    @PostMapping("/todo/uploadWithFile")
    public String sendWithFile(@RequestParam("file") MultipartFile multipartFile,
                               Model model) {
        String message = "";
        try (InputStream inputStream = multipartFile.getInputStream()) {
            message = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ToDoEntity toDoEntity = new ToDoEntity(message);
        appService.create(toDoEntity);
        return "redirect:/";
    }

    @RequestMapping(value = "/todo/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String delete(@PathVariable("id") int id) throws Exception {
        ToDoEntity toDoEntity = appService.get(id);
        String res = objectMapper.writeValueAsString(toDoEntity);
//        appService.delete(toDoEntity);
        kafkaService.delete(toDoEntity);
        System.out.println("sent");
        return "redirect:/";
    }

    @PostMapping("/todo/update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("task") String updatedTask) throws Exception {
        ToDoEntity toDoEntity = appService.get(id);
        toDoEntity.setCurrentTask(updatedTask);
        String res = objectMapper.writeValueAsString(toDoEntity);
//        appService.create(toDoEntity);
        kafkaService.create(toDoEntity);
        return "redirect:/";
    }
}
