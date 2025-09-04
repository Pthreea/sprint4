public class Subtask extends Task{
    private String epicId;

    public Subtask(String name, String epicId){
        super(name);
        this.epicId = epicId;
    }

    public String getEpicId(){
        return epicId;
    }
}
