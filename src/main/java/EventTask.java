public class EventTask extends Task {
    protected String start;
    protected String end;

    public EventTask(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override 
    public String toString() {
        if (this.completed) {
            return "[D][X] " + super.name + " (from: " + this.start + " to: " + this.end + ")";
        } else {
            return "[D][ ] " + super.name + " (" + this.start + " " + this.end + ")";
        }
    }
}
