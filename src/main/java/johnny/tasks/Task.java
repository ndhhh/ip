package johnny.tasks;

/**
 * Represents a task that the user wishes to store and keep note of
 */
public class Task {
    protected String name;
    protected boolean completed;

    /**
     * Creates a new Task with the given name
     * @param name
     */
    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /**
     * Creates a new Task with the given name and boolean
     * @param name
     * @param completed Whether task is done
     */
    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.completed;
    }

    public void markComplete() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    /**
     * Returns the string format used in storing the task in the save file
     * @return String format
     */
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
