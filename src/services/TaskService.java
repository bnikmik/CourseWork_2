package services;

import exceptions.TaskNotFoundExceptions;
import task.Task;

import java.time.LocalDate;
import java.util.*;
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

    public Task remove(int id) throws TaskNotFoundExceptions {
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

    public Map<LocalDate, List<Task>> getAllGroupByDate () {
        return taskMap.values().stream()
                .collect(Collectors.groupingBy(task -> task.getDateTime().toLocalDate()));
    }

    public Collection<Task> getRemovedTasks() {
        return new ArrayList<>(removedTasks);
    }

    public Task updateTitle(int i, String title) {
         taskMap.get(i).setTitle(title);
        return taskMap.get(i);

    }

    public Task updateDescription(int i, String desc) {
        taskMap.get(i).setDescription(desc);
        return taskMap.get(i);
    }
}
