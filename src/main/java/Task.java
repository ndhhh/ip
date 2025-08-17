public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
