import api.Task;
import exception.IncorrectArgumentException;
import exception.TaskNotFoundExceptions;
import service.TaskService;
import task.DailyTask;
import task.MonthlyTask;
import task.OneTimeTask;
import task.YearlyTask;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        DailyTask dailyTask = new DailyTask(
                "dailyTask",
                Task.Type.PERSONAL,
                "сделай это",
                LocalDateTime.of(2023, 1, 24, 23, 10)
        );
        OneTimeTask oneTimeTask = new OneTimeTask(
                "oneTimeTask",
                Task.Type.PERSONAL,
                "сделай это",
                LocalDateTime.of(2023, 1, 24, 19, 5)
        );

        MonthlyTask monthlyTask = new MonthlyTask(
                "dsad",
                Task.Type.PERSONAL,
                "сделай это",
                LocalDateTime.of(2023, 1, 24, 13, 5)
        );
        YearlyTask yearlyTask = null;

        try {
            yearlyTask = new YearlyTask(
                    "asd",
                    Task.Type.PERSONAL,
                    "сделай это",
                    LocalDateTime.of(2023, 2, 25, 13, 5)
            );
        } catch (IncorrectArgumentException e) {
            System.out.println("Title не должен быть равен: <" + e.getMessage() +">");
        }

        TaskService taskService = new TaskService();
        taskService.add(dailyTask);
        taskService.add(oneTimeTask);
        taskService.add(monthlyTask);
        taskService.add(yearlyTask);

        System.out.println("=================");
        System.out.println(taskService.getAllGroupByDate());
        System.out.println("=================");

        try {
            System.out.println(taskService.remove(4));
        } catch (TaskNotFoundExceptions e) {
            System.out.println("Нельзя удалить! Задачи не существует! ");
        }

        try {
            System.out.println(taskService.remove(3));
        } catch (TaskNotFoundExceptions e) {
            System.out.println("Нельзя удалить! Задачи не существует! ");
        }

        taskService.updateDescription(1, "asd111sad");


        System.out.println(taskService.getRemovedTasks());

        System.out.println(oneTimeTask.appearsIn(LocalDate.of(2023, 1, 26)));

        System.out.println(taskService.getAllByDate(LocalDate.of(2022, 1, 24)));



    }


}