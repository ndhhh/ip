package johnny.ui;
import java.util.NoSuchElementException;
import java.util.Scanner;

import johnny.tasklist.TaskList;
import johnny.tasks.Task;

/**
 * Ui class for handling interactions with the user, such as reading user input and printing messages
 */
public class Ui {
    private static final String LINE = "__________________________________________________";
    private static final String NAME = "Johnny";
    private Scanner sc;

    /**
     * Creates a new Ui object with a scanner to detect user input
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a new line
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Reads from the user's input in System.in and returns the string read
     * @return String read from user input
     */
    public String readCommand() {
        try {
            String fullCommand = sc.nextLine();
            return fullCommand;
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Error reading command: " + e.getMessage());
            return null;
        } 
    }

    public void closeScanner() {
        sc.close();
    }

    public void printGreeting() {
        this.printLine();
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?");
        this.printLine();
    }

    public void printByeMessage() {
        System.out.println(LINE + "\nBye. Hope to see you again!\n" + LINE);
    }

    public void printNumberOfTasks(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    public void printAddTaskMessage(TaskList tasks, Task t) {
        this.printLine();
        System.out.println("Task added: \n" + t.toString() + "\n");
        this.printNumberOfTasks(tasks);
        this.printLine();
    }

    public void printMarkMessage(TaskList tasks, int index) {
        this.printLine();
        System.out.println("Marked task as done: ");
        System.out.println(tasks.getTask(index).toString());
        this.printLine();
    }

    public void printUnmarkMessage(TaskList tasks, int index) {
        this.printLine();
        System.out.println("Marked task as undone: ");
        System.out.println(tasks.getTask(index).toString());
        this.printLine();
    }

    public void printDeleteMessage(TaskList tasks, Task t) {
        this.printLine();
        System.out.println("Task deleted successfully");
        System.out.println(t.toString());
        this.printNumberOfTasks(tasks);
        this.printLine();
    }

    public void printTaskList(TaskList tasks) {
        this.printLine();
        System.out.println(tasks.toString());
        this.printLine();
    }

    public void printInvalidNumberError() {
        this.printLine();
        System.out.println("You inputted an invalid number after your command.");
        this.printLine();
    }

    public void printNoNumberError() {
        System.out.println(LINE + "\nYour command should be accompanied by a number.\n" + LINE);
    }

    public void printNoTaskNameError() {
        System.out.println(LINE + "\nThe description of a task cannot be empty\n" + LINE);
    }

    public void printDeadlineError() {
        System.out.println(LINE + "\nThere is no/more than 1 deadline provided.\nPlease use the format: deadline [task name] /by [deadline]\n" + LINE);
    }

    public void printDateError() {
        this.printLine();
        System.out.println("Invalid date format used. Please follow this format: [dd/MM/yyyy]");
        this.printLine();
    }

    /**
     * Prints the exception message when a date or time cannot be parsed
     * @param e The exception thrown by the parser
     */
    public void printDateTimeException(Exception e) {
        this.printLine();
        System.out.println("Problem with parsing date/time: " + e.getMessage());
        this.printLine();
    }
}
