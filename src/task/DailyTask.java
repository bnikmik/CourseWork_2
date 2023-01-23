package task;

import enums.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(String title, Type type, String description, LocalDateTime dateTime) {
        super(title, type, description, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(getDateTime().toLocalDate());
    }
}
