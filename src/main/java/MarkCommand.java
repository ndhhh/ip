public class MarkCommand extends Command {
    protected int index; 

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override 
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            ui.printInvalidNumberError();
            return;
        }
        tasks.markComplete(this.index);
        ui.printMarkMessage(tasks, this.index);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
