public class DeleteCommand extends Command {
    protected int index; 

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override 
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            ui.printInvalidNumberError();
            return;
        }
        
        Task deleted = tasks.deleteTask(this.index);
        ui.printDeleteMessage(tasks, deleted);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
