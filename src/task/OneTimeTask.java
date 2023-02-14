package task;

import api.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, Type type, String description, LocalDateTime dateTime) {
        super(title, type, description, dateTime);
    }

    /*/
    check appears date
    */
    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.isAfter(LocalDate.now().minusDays(1))
                && localDate.isEqual(getDateTime().toLocalDate());
    }
}
