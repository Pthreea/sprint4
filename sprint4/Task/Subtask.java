package sprint4.Task;

public class Subtask extends Task {
    final private String epicId;

    public Subtask(String name,String description, String epicId){
        super(name, description);
        this.epicId = epicId;
    }

    public String getEpicId(){
        return epicId;
    }

}
