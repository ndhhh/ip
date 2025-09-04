package johnny.bot;

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

    /**
     * Creates a new instance of Johnny with the specified file path to read and
     * write from storage
     * 
     * @param filePath File path of storage text file
     */
    public Johnny(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load(this.ui));
    }

    /**
     * Runs the bot, starts by printing a greeting. The other packages like Ui
     * handle the reading of commands
     * or parsing and storing and saving tasks
     */
    public void run() {
        this.ui.printGreeting();
        boolean isBye = false;
        while (!isBye) {
            String fullCommand = ui.readCommand();
            // ui.printLine();
            Command command = Parser.read(fullCommand, this.storage, this.tasks, this.ui);
            if (command != null) {
                command.execute(this.tasks, this.ui, this.storage);
                isBye = command.isBye();
            }
            // ui.printLine();
        }

        ui.closeScanner();
    }

    /**
     * Parses the input by the user and returns a string response to
     * be fed to the GUI.
     * 
     * @param input User input in the text field in the GUI
     * @return String to be displayed in a dialog box in the GUI
     */
    public String generateResponse(String input) {
        Command command = Parser.read(input, this.storage, this.tasks, this.ui);
        String msg = command.execute(this.tasks, this.ui, this.storage);
        return msg;
    }

    public String mirrorInput(String input) {
        return "Johnny heard: " + input;
    }

    public static void main(String[] args) {
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        Johnny johnny = new Johnny("data/tasks.txt");
        johnny.run();
    }
}
