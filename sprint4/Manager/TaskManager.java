package sprint4.Manager;

import sprint4.Task.Epic;
import sprint4.Task.Subtask;
import sprint4.Task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskManager {
    final private HashMap<String, Task> tasks;
    final private HashMap<String, Epic> epics;
    final private HashMap<String, Subtask> subtasks;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void addEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void addSubtask(Subtask subtask) {
        if (epics.containsKey(subtask.getEpicId())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            epic.addSubtaskId(subtask.getId());
            epic.updateStatus(subtasks);
        } else {
            System.out.println("Nothing");
        }
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtaskIds();
            epic.updateStatus(subtasks);
        }
    }

    public Task getTaskById(String id) {
        return tasks.get(id);
    }

    public Epic getEpicById(String id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(String id) {
        return subtasks.get(id);
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            epic.updateStatus(subtasks);
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.updateStatus(subtasks);
            }
        }
    }

    public void deleteTaskById(String id) {
        tasks.remove(id);
    }

    public void deleteEpicById(String id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            for (String subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
            epics.remove(id);
        }
    }

    public void deleteSubtaskById(String id) {
        if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.getSubtaskIds().remove(id);
                epic.updateStatus(subtasks);
            }
            subtasks.remove(id);
        }
    }

    public ArrayList<Task> getAllTasks(){
        return  new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics(){
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks(){
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Subtask> getAllSubtasksForEpic(String epicId){
        ArrayList<Subtask> result = new ArrayList<>();
        Epic epic = epics.get(epicId);
        if(epic != null){
            for(String subtaskId : epic.getSubtaskIds()){
             Subtask subtask = subtasks.get(subtaskId);
             if(subtask != null){
                 result.add(subtask);
             }
            }
        }
        return result;
    }
}
