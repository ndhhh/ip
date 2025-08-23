import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDate deadline;
    protected String formattedDate;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");;

    public DeadlineTask(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
        this.formattedDate = deadline.format(formatter);
    }

    public DeadlineTask(String name, boolean completed, LocalDate deadline) {
        super(name, completed);
        this.deadline = deadline;
        this.formattedDate = deadline.format(formatter);
    }

    @Override 
    public String getStoredString() {
        if (completed) return "D|1|" + this.name + "|" + formattedDate;
        return "D|0|" + this.name + "|" + formattedDate;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[D][X] " + super.name + " (by: " + formattedDate + ")";
        } else {
            return "[D][ ] " + super.name + " (by: " + formattedDate + ")";
        }
    }
}
