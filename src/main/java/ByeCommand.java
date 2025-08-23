public class ByeCommand extends Command {
    public ByeCommand() { }

    @Override 
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.printByeMessage();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
