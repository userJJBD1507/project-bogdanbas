package usa.bogdan.web.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "todoentity_table")
public class ToDoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "currentTask", length = 1500)
    private String currentTask;
    public ToDoEntity() {

    }

    public ToDoEntity(String currentTask) {
        this.currentTask = currentTask;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    @Override
    public String toString() {
        return "ToDoEntity{" +
                "id=" + id +
                ", currentTask='" + currentTask + '\'' +
                '}';
    }
}
