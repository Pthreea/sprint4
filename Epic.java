import java.util.ArrayList;

public class Epic extends Task{
    private ArrayList<Subtask> subtasks;

    public Epic(String name){
        super(name);
        this.subtasks = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask){
        if(subtask.getEpicId().equals(this.getId())){
            subtasks.add(subtask);
            updateStatus();
        }
    }

    public ArrayList<Subtask> getSubtasks(){
        return subtasks;
    }

    public void updateStatus(){
        boolean allDone = true;
        boolean anyInProgress = false;

        for(Subtask subtask : subtasks){
            if(subtask.getStatus() != Status.DONE){
                allDone = false;
            }
            if(subtask.getStatus() == Status.IN_PROGRESS){
                anyInProgress = true;
            }
        }

        if(allDone){
            setStatus(Status.DONE);
        }else if(anyInProgress){
            setStatus(Status.IN_PROGRESS);
        }else {
            setStatus(Status.NEW);
        }
    }

    @Override
    public void setStatus(Status status){
        super.setStatus(status);
        if(status == Status.DONE){
            for (Subtask subtask : subtasks){
                subtask.setStatus(Status.DONE);
            }
        }
    }
}
