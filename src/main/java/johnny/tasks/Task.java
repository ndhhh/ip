package johnny.tasks;
public class Task {
    protected String name;
    protected boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public void markComplete() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    public String getStoredString() {
        if (completed) return "T-1-" + this.name;
        return "T|0|" + this.name;
    }

    public boolean contains(String subString) {
        return this.name.contains(subString);
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
