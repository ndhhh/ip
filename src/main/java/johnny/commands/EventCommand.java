package johnny.commands;
import java.time.LocalDateTime;
import java.time.LocalTime;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.tasks.EventTask;
import johnny.tasks.Task;
import johnny.ui.Ui;

public class EventCommand extends Command {
    protected String name;
    protected LocalDateTime start;
    protected LocalTime end;

    public EventCommand(String name, LocalDateTime start, LocalTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new EventTask(this.name, this.start, this.end);
        tasks.addTask(newTask);
        ui.printAddTaskMessage(tasks, newTask);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
