package johnny.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

import johnny.tasklist.TaskList;
import johnny.tasks.Task;

/**
 * Ui class for handling interactions with the user, such as reading user input
 * and printing messages
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
    public String printLine() {
        System.out.println(LINE);
        return LINE;
    }

    /**
     * Reads from the user's input in System.in and returns the string read
     * 
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

    public String printMessage(String string) {
        String msg = string;
        System.out.println(msg);
        return msg;
    }

    public String printGreeting() {
        String msg = "Hello! I'm " + NAME + "\nWhat can I do for you?";
        System.out.println(msg);
        return msg;

    }

    public String printByeMessage() {
        String msg = "Bye. Hope to see you again!";
        System.out.println(msg);
        return msg;
    }

    public String printNumberOfTasks(TaskList tasks) {
        String msg = "Now you have " + tasks.size() + " tasks in your list.";
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
        return msg;
    }

    public String printAddTaskMessage(TaskList tasks, Task t) {
        String msg = "Task added: \n" + t.toString() + "\n" + printNumberOfTasks(tasks);
        return msg;
    }

    public String printMarkMessage(TaskList tasks, int index) {
        String msg = "Marked task as done: \n" + tasks.getTask(index).toString();
        return msg;
    }

    public String printUnmarkMessage(TaskList tasks, int index) {
        String msg = "Marked task as undone: \n" + tasks.getTask(index).toString();
        return msg;
    }

    public String printDeleteMessage(TaskList tasks, Task t) {
        String msg = "Task deleted successfully.\n" + t.toString() + "\n" + printNumberOfTasks(tasks);
        return msg;
    }

    public String printTaskList(TaskList tasks) {
        return tasks.toString();
    }

    public String printInvalidNumberError() {
        return "You inputted an invalid number after your command.";
    }

    public String printNoNumberError() {
        return "Your command should be accompanied by a number.";
    }

    public String printNoTaskNameError() {
        return "The description of a task cannot be empty";
    }

    public String printNoFindDescriptionError() {
        return "The description of a task you are finding cannot be empty";
    }

    public String printDeadlineError() {
        return "There is no/more than 1 deadline provided.\\n" + //
                "Please use the format: deadline [task name] /by [deadline]";
    }

    public String printDateError() {
        return "Invalid date format used. Please follow this format: [dd/MM/yyyy]";
    }

    /**
     * Prints the exception message when a date or time cannot be parsed
     * 
     * @param e The exception thrown by the parser
     */
    public String printDateTimeException(Exception e) {
        return "Problem with parsing date/time: " + e.getMessage();
    }
}
