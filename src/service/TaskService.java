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

    /*/
    add a new task
     */
    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    /*/
    delete a task
     */
    public Task remove(int id) throws TaskNotFoundExceptions {
        Task task = taskMap.remove(id);
        if (task != null) {
            removedTasks.add(task);
            return task;
        } else {
            throw new TaskNotFoundExceptions();
        }
    }

    /*/
    get all the tasks in the collection
     */
    public Collection<Task> getAllByDate(LocalDate localDate) {
        return taskMap.values()
                .stream()
                .filter(task -> task.appearsIn(localDate))
                .collect(Collectors.toList());
    }

    /*/
    get all tasks by date
     */
    public Map<LocalDate, Collection<Task>> getAllGroupByDate() {
        return taskMap.values().stream()
                .collect(Collectors.toMap(task -> task.getDateTime().toLocalDate(),
                        task -> {
                            Collection<Task> taskCollection = new ArrayList<>();
                            taskCollection.add(task);
                            return taskCollection;
                        }, (o1, o2) -> {
                            o1.addAll(o2);
                            return o1;
                        }
                ));

    }

    /*/
    get all tasks by date (second version)
     */
    public Map<LocalDate, List<Task>> getAllGroupByDate2() {
        return taskMap.values().stream()
                .collect(Collectors.groupingBy(task -> task.getDateTime().toLocalDate(),
                        Collectors.toCollection(ArrayList::new)));
    }


    /*/
    get deleted tasks
     */
    public Collection<Task> getRemovedTasks() {
        return new ArrayList<>(removedTasks);
    }

    /*/
    update the task title
     */
    public Task updateTitle(int i, String title) {
        taskMap.get(i).setTitle(title);
        return taskMap.get(i);

    }

    /*/
    update the task description
     */
    public Task updateDescription(int i, String desc) {
        taskMap.get(i).setDescription(desc);
        return taskMap.get(i);
    }
}
