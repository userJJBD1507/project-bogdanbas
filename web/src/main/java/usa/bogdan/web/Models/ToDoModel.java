package usa.bogdan.web.Models;

public class ToDoModel {
    private String name;

    public ToDoModel() {

    }

    public ToDoModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ToDoModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
