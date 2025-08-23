package johnny.commands;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() { }

    @Override 
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
