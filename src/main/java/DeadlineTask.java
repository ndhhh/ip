public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
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
