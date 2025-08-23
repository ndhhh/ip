package johnny.commands;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isBye();
}
