package sprint4.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {
    final private ArrayList<String> subtaskIds;

    public Epic(String name, String description){
        super(name,description);
        this.subtaskIds = new ArrayList<>();
    }

    public void addSubtaskId(String subtaskId){
        subtaskIds.add(subtaskId);
    }

    public ArrayList<String> getSubtaskIds(){
        return subtaskIds;
    }

    public void clearSubtaskIds(){
        subtaskIds.clear();
    }

    public void updateStatus(HashMap<String, Subtask > allSubtasks){
        boolean allDone = true;
        boolean anyProgress = false;

        for (String subtaskId : subtaskIds){
            Subtask subtask = allSubtasks.get(subtaskId);
            if(subtask != null){
                if(subtask.getStatus() != Status.DONE){
                    allDone = false;
                }
                if(subtask.getStatus() == Status.IN_PROGRESS){
                    anyProgress = true;
                }
            }
        }

        if(allDone){
            setStatus(Status.DONE);
        }else if(anyProgress){
            setStatus(Status.IN_PROGRESS);
        }else {
            setStatus(Status.NEW);
        }
    }

    @Override
    public void setStatus(Status status){
        super.setStatus(status);
    }

}
