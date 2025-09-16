package sprint4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sprint4.Manager.InMemoryHistoryManager;
import sprint4.Manager.InMemoryTaskManager;
import sprint4.Manager.HistoryManager;
import sprint4.Manager.TaskManager;
import sprint4.Task.Task;
import sprint4.Task.Status;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {

    private TaskManager taskManager;
    private HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        taskManager = new InMemoryTaskManager();
        historyManager = new InMemoryHistoryManager();
    }

    // Test for adding a new task
    @Test
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", Status.NEW);
        taskManager.addTask(task);
        final Task savedTask = taskManager.getTaskById(task.getId()); // Assume the method name matches your design

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getAllTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }


    @Test
    void add() {
        Task task = new Task("History Task", "Task to add to history", Status.NEW);
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();

        assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
        assertEquals(1, history.size(), "Неверное количество записей в истории.");
        assertEquals(task, history.get(0), "Неправильная задача в истории.");
    }
}