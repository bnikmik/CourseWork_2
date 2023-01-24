package task;

import enums.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, Type type, String description, LocalDateTime dateTime) {
        super(title, type, description, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(LocalDate.now().minusDays(1))
                && localDate.getDayOfWeek().equals(getDateTime().getDayOfWeek());
    }
}
