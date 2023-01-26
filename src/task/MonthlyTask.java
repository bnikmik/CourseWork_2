package task;

import api.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, Type type, String description, LocalDateTime dateTime) {
        super(title, type, description, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isEqual(getDateTime().toLocalDate()) ||
                localDate.getDayOfMonth() == getDateTime().getDayOfMonth() && localDate.isAfter(getDateTime().toLocalDate());
    }
}
