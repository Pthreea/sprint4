import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<String,Task> tasks;
    private HashMap<String,Epic> epics;
    private HashMap<String,Subtask> subtasks;

    public TaskManager(){
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
    }

    public void addTask(Task task){
        tasks.put(task.getId(),task);
    }

    public void addEpic(Epic epic){
        epics.put(epic.getId(),epic);
    }

    public void addSubtask(Subtask subtask){
        if(epics.containsKey(subtask.getEpicId())){
            subtasks.put(subtask.getId(),subtask);
            Epic epic = epics.get(subtask.getEpicId());
            epic.addSubtask(subtask);
        }else{
            System.out.println("Эпик для подзадачи не найден");
        }
    }

    public void clearAllTasks(){
        epics.clear();
        tasks.clear();
        subtasks.clear();
    }

    public Task getTaskById(String id){
        return  tasks.get(id);
    }

    public Epic getEpicBuId(String id){
        return  epics.get(id);
    }

    public Subtask getSubtaskById(String id){
        return subtasks.get(id);
    }
    public void updateTask(Task task){
        if(tasks.containsKey(task.getId())){
            tasks.put(task.getId(),task);
        }
    }

    public void updateEpic(Epic epic){
        if(epics.containsKey(epic.getId())){
            epics.put(epic.getId(),epic);
            epic.updateStatus();
        }
    }

    public void updateSubtask(Subtask subtask){
        if(subtasks.containsKey(subtask.getId())){
            subtasks.put(subtask.getId(),subtask);
            Epic epic = epics.get(subtask.getEpicId());
            if(epic!= null){
                epic.updateStatus();
            }
        }
    }

    public void deleteTaskById(String id){
        tasks.remove(id);
    }

    public void deleteEpicById(String id){
        if(epics.containsKey(id)){
            Epic epic = epics.get(id);
            for (Subtask subtask : epic.getSubtasks()){
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        }
    }

    public void deleteSubtaskById(String id){
        if(subtasks.containsKey(id)){
            Subtask subtask = subtasks.get(id);
            Epic epic = epics.get(subtask.getEpicId());
            if(epic != null){
                epic.getSubtasks().remove(subtask);
                epic.updateStatus();
            }
        }
    }

    public ArrayList<Subtask> getAllSubtasksForEpic(String epicId){
        ArrayList<Subtask> result = new ArrayList<>();
        Epic epic = epics.get(epicId);
        if(epic != null){
            result.addAll(epic.getSubtasks());
        }
        return result;
    }
}
