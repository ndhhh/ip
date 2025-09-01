package johnny;

import johnny.commands.Command;
import johnny.commands.DeadlineCommand;
import johnny.commands.ByeCommand;
import johnny.commands.DeleteCommand;
import johnny.commands.EventCommand;
import johnny.commands.ListCommand;
import johnny.commands.TodoCommand;
import johnny.commands.MarkCommand;
import johnny.commands.UnmarkCommand;
import johnny.parser.Parser;
import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.ui.Ui;

public class Johnny {
    private String filePath;
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private final String LINE = "__________________________________________________";

    public Johnny(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load(this.ui));
    }

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

    // public void read() {
    // Scanner sc = new Scanner(System.in);
    // String input = "";

    // while (true)
    // {
    // input = sc.nextLine();
    // if (input.equals("bye")){
    // this.storage.save(tasks);
    // System.out.println(LINE + "\nBye. Hope to see you again!\n" + LINE);
    // break;
    // } else if (input.equals("list")) {
    // System.out.println(this.tasksToString());
    // } else if (input.startsWith("mark ") || input.startsWith("unmark ")) {
    // String[] strings = input.split(" ");
    // this.parseMark(strings);
    // } else if (input.startsWith("todo ")) {
    // String[] strings = input.split(" ");
    // this.parseTodo(strings);
    // } else if (input.startsWith("deadline ")) {
    // String[] strings = input.split("/by ");
    // this.parseDeadline(strings);
    // } else if (input.startsWith("event ")) {
    // this.parseEvent(input);
    // } else if (input.startsWith("delete ")) {
    // this.parseDelete(input);
    // } else {
    // System.out.println(LINE + "\n" + "Invalid command." + "\n" + LINE);
    // }
    // }

    // sc.close();
    // }

    public static void main(String[] args) {
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        Johnny johnny = new Johnny("data/tasks.txt");
        johnny.run();
    }
}
