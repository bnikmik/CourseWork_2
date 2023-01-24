package services;

import exceptions.TaskNotFoundExceptions;
import task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class TaskService {
    private final Map<Integer, Task> taskMap;
    private final Collection<Task> removedTasks;

    public TaskService() {
        this.taskMap = new HashMap<>();
        this.removedTasks = new ArrayList<>();
    }

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    public Task remove (int id) throws TaskNotFoundExceptions{
        if (taskMap.containsKey(id)) {
            Task tempTask = taskMap.get(id);
            removedTasks.add(tempTask);
            taskMap.remove(id);
            return tempTask;
        } else {
            throw new TaskNotFoundExceptions();
        }
    }

    public Collection<Task> getAllByDate(LocalDate localDate) {
        return taskMap.values().stream().filter(task -> task.appearsIn(localDate)).collect(Collectors.toList());
    }
}
