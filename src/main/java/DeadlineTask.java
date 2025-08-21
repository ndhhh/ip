public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public DeadlineTask(String name, boolean completed, String deadline) {
        super(name, completed);
        this.deadline = deadline;
    }

    @Override 
    public String getStoredString() {
        if (completed) return "D|1|" + this.name + "|" + deadline;
        return "D|0|" + this.name + "|" + deadline;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[D][X] " + super.name + " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + super.name + " (by: " + this.deadline + ")";
        }
    }
}
