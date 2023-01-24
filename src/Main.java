import enums.Type;
import exceptions.IncorrectArgumentException;
import exceptions.TaskNotFoundExceptions;
import services.TaskService;
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
                Type.PERSONAL,
                "сделай это",
                LocalDateTime.of(2023, 1, 24, 23, 10)
        );
        OneTimeTask oneTimeTask = new OneTimeTask(
                "oneTimeTask",
                Type.PERSONAL,
                "сделай это",
                LocalDateTime.of(2023, 1, 26, 19, 5)
        );

        MonthlyTask monthlyTask = new MonthlyTask(
                "dsad",
                Type.PERSONAL,
                "сделай это",
                LocalDateTime.of(2023, 1, 24, 13, 5)
        );
        YearlyTask yearlyTask = null;

        try {
            yearlyTask = new YearlyTask(
                    "",
                    Type.PERSONAL,
                    "сделай это",
                    LocalDateTime.of(2023, 1, 24, 13, 5)
            );
        } catch (IncorrectArgumentException e) {
            System.out.println("Title не должен быть равен: <" + e.getMessage() +">");
        }

        TaskService taskService = new TaskService();
        taskService.add(dailyTask);
        taskService.add(oneTimeTask);
        taskService.add(monthlyTask);
        taskService.add(yearlyTask);

        try {
            System.out.println(taskService.remove(4));
        } catch (TaskNotFoundExceptions e) {
            System.out.println("Нельзя удалить! Задачи не существует! ");
        }

        System.out.println(oneTimeTask.appearsIn(LocalDate.of(2023, 1, 26)));

        System.out.println(taskService.getAllByDate(LocalDate.of(2022, 1, 24)));



    }


}