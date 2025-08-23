package johnny.tasks;
public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }

    public TodoTask(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    public String getStoredString() {
        if (completed) return "T|1|" + this.name;
        return "T|0|" + this.name;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[T][X] " + super.name;
        } else {
            return "[T][ ] " + super.name;
        }
    }
}
