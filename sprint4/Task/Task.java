package sprint4.Task;

import java.util.Objects;

public class Task {
    private static int idCounter = 0;
    private final String id;
    final private String name;
    private Status status;
    private String description;

    public Task(String name,String description) {
        this.id = generateId();
        this.name = name;
        this.status = Status.NEW;
        this.description = description;
    }

    private static String generateId() {
        return "TASK-" + (++idCounter);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return Objects.equals(id, task.id);
    }
}

