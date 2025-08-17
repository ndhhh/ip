public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
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
