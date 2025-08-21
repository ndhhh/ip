public abstract class Command {
    /**
     * 
     * @param tasks The list of tasks currently manipulated by Johnny
     * @param ui The text UI for printing lines
     * @param storage For storage manipulation to store the task list
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    
    /**
     * Checks if the command is a Bye command
     * @return true if the command is a Bye command
     */
    public abstract boolean isBye();

}
