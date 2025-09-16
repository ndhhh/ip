package johnny.bot;

import java.util.function.Consumer;

import johnny.commands.Command;

import johnny.parser.Parser;
import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.ui.Ui;

/**
 * This class is the main class of the bot, starts the bot process by running it
 */
public class Johnny {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Consumer<String> guiErrorCallback;

    /**
     * Creates a new instance of Johnny with the specified file path to read and
     * write from storage
     * 
     * @param filePath         File path of storage text file
     * @param guiErrorCallback For calling to the GUI show error function to display
     *                         an error
     */
    public Johnny(String filePath, Consumer<String> guiErrorCallback) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.guiErrorCallback = guiErrorCallback;
        this.tasks = new TaskList(this.storage.load(this.ui));
    }

    /**
     * Parses the input by the user and returns a string response to
     * be fed to the GUI.
     * 
     * @param input User input in the text field in the GUI
     * @return String to be displayed in a dialog box in the GUI
     */
    public String generateResponse(String input) {
        assert this.storage != null : "Storage cannot be null";
        assert this.tasks != null : "Task list cannot be null";
        assert this.ui != null : "UI cannot be null";
        assert input != null : "User input cannot be null";
        Command command = Parser.read(input, this.storage, this.tasks, this.ui);
        assert command != null : "Returned command should not be null";
        String msg = command.execute(this.tasks, this.ui, this.storage);
        assert msg != null : "Returned message should not be null";
        assert !msg.isEmpty() : "Returned message should not be empty.";
        return msg;
    }

    public String greeting() {
        return this.ui.printGreeting();
    }
}
