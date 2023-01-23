import enums.Type;
import exceptions.TaskNotFoundExceptions;
import services.TaskService;
import task.DailyTask;
import task.MonthlyTask;
import task.OneTimeTask;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        DailyTask dailyTask = new DailyTask(
                "dailyTask",
                Type.PERSONAL,
                "сделай это",
                LocalDateTime.of(2023,1,25,23,10)
        );
        OneTimeTask oneTimeTask = new OneTimeTask(
                "oneTimeTask",
                Type.PERSONAL,
                "сделай это",
                LocalDateTime.of(2023,12,13,13,5)
        );
        MonthlyTask monthlyTask = new MonthlyTask(
                "dsad",
                Type.PERSONAL,
                "сделай это",
                LocalDateTime.of(2023,4,13,13,5)
        );

        TaskService taskService = new TaskService();
        taskService.add(dailyTask);
        taskService.add(oneTimeTask);
        taskService.add(monthlyTask);

        try {
            System.out.println(taskService.remove(3));
        } catch (TaskNotFoundExceptions e) {
            System.out.println("Нельзя удалить! Задачи не существует! ");
        }

        System.out.println(taskService.getAllByDate(LocalDate.of(2023, 12, 13)));
    }
}