package task;

import api.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask(String title, Type type, String description, LocalDateTime dateTime) {
        super(title, type, description, dateTime);
    }

    /*/
    check appears date
     */
    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isEqual(getDateTime().toLocalDate()) || localDate.isAfter(getDateTime().toLocalDate());
    }
}
