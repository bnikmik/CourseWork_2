package service;

import exception.TaskNotFoundExceptions;
import api.Task;

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
        Task task = taskMap.remove(id);
        if (task!=null) {
            removedTasks.add(task);
            return task;
        } else {
            throw new TaskNotFoundExceptions();
        }
    }

    public Collection<Task> getAllByDate(LocalDate localDate) {
        return taskMap.values()
                .stream()
                .filter(task -> task.appearsIn(localDate))
                .collect(Collectors.toList());
    }

    public Map<LocalDate, Collection<Task>> getAllGroupByDate () {
        return taskMap.values().stream()
                .collect(Collectors.toMap(task -> task.getDateTime().toLocalDate()
                        ,task -> {
                            Collection<Task> tasks = new ArrayList<>();
                            tasks.add(task);
                            return tasks;
                        },(o, o2) -> {
                            o.addAll(o2);
                            return o;
                        }));
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
