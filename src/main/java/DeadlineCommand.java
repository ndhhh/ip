import java.time.LocalDate;

public class DeadlineCommand extends Command {
    protected String name;
    protected LocalDate deadline;

    public DeadlineCommand(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override 
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new DeadlineTask(this.name, this.deadline);
        tasks.addTask(newTask);
        ui.printAddTaskMessage(tasks, newTask);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
