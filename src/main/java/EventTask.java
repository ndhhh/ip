import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDateTime start;
    protected LocalTime end;
    protected String formattedStart;
    protected String formattedEnd;
    protected static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    protected static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public EventTask(String name, LocalDateTime start, LocalTime end) {
        super(name);
        this.start = start;
        this.end = end;
        this.formattedStart = start.format(dateTimeFormatter);
        this.formattedEnd = end.format(timeFormatter);
    }

    public EventTask(String name, boolean completed, LocalDateTime start, LocalTime end) {
        super(name, completed);
        this.start = start;
        this.end = end;
        this.formattedStart = start.format(dateTimeFormatter);
        this.formattedEnd = end.format(timeFormatter);
    }

    @Override 
    public String getStoredString() {
        if (completed) return "E|1|" + this.name + "|" + this.formattedStart + "|" + this.formattedEnd;
        return "E|0|" + this.name + "|" + this.formattedStart + "|" + this.formattedEnd;
    }

    @Override 
    public String toString() {
        if (this.completed) {
            return "[D][X] " + super.name + " (from: " + this.formattedStart + " to: " + this.formattedEnd + ")";
        } else {
            return "[D][ ] " + super.name + " (from: " + this.formattedStart + " to: " + this.formattedEnd + ")";
        }
    }
}
