public class TodoCommand extends Command {
    protected String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    @Override 
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new TodoTask(this.name);
        tasks.addTask(newTask);
        ui.printAddTaskMessage(tasks, newTask);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
