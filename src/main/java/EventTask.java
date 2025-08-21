public class EventTask extends Task {
    protected String start;
    protected String end;

    public EventTask(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public EventTask(String name, boolean completed, String start, String end) {
        super(name, completed);
        this.start = start;
        this.end = end;
    }

    @Override 
    public String getStoredString() {
        if (completed) return "E|1|" + this.name + "|" + this.start + "|" + this.end;
        return "E|0|" + this.name + "|" + this.start + "|" + this.end;
    }

    @Override 
    public String toString() {
        if (this.completed) {
            return "[D][X] " + super.name + " (from: " + this.start + " to: " + this.end + ")";
        } else {
            return "[D][ ] " + super.name + " (from: " + this.start + " to: " + this.end + ")";
        }
    }
}
