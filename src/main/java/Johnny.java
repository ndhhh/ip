import java.util.ArrayList;
import java.util.Scanner;

public class Johnny {

    private ArrayList<Task> tasks = new ArrayList<>();
    private final String LINE = "__________________________________________________";

    public Johnny() {

    }

    public String greeting() {
        String name = "Johnny";
        
        return LINE + "\n" + "Hello! I'm " + name + "\nWhat can I do for you?\n" + LINE;
    }

    public String tasksToString() {
        StringBuilder result = new StringBuilder(LINE);
        result.append("\n");

        if (this.tasks.isEmpty()) {
            return "No tasks in list";
        }

        for (int i = 0; i < tasks.size(); i++)
        {
            int num = i + 1;
            String str = String.format(". %s", tasks.get(i).toString());
            result.append(num);
            result.append(str);
            result.append("\n");
        }

        return result.append(LINE).toString();
    }

    public void read() {
        Scanner sc = new Scanner(System.in);
        String input = "";

        while (true)
        {
            input = sc.nextLine();
            if (input.equals("bye"))
            {
                System.out.println(LINE + "\nBye. Hope to see you again!\n" + LINE);
                break;
            } else if (input.equals("list")) {
                System.out.println(this.tasksToString());
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                String[] strings = input.split(" ");
                this.parseMark(strings);
            } else if (input.startsWith("todo")) {
                String[] strings = input.split(" ");
                this.parseTodo(strings);
            } else if (input.startsWith("deadline")) {
                String[] strings = input.split("/by ");
                this.parseDeadline(strings);
            } else if (input.startsWith("event")) {
                this.parseEvent(input);
            } else {
                System.out.println(LINE + "\n" + "Invalid command." + "\n" + LINE);
            }
        }

        sc.close();
    }

    public void parseEvent(String input) {
        String[] firstParse = input.split("/to ");
        if (firstParse.length != 2) {
            System.out.println(LINE + "\nThere is no/more than 1 event end time provided.\nPlease use the format: event [task name] /from [start time] /to [end time]\n" + LINE);
            return;
        }

        String[] secondParse = firstParse[0].split("/from ");
        if (secondParse.length != 2) {
            System.out.println(LINE + "\nThere is no/more than 1 event start time provided.\nPlease use the format: event [task name] /from [start time] /to [end time]\n" + LINE);
            return;
        }

        String[] eventName = secondParse[0].split(" ");
        if (eventName.length < 2) {
            System.out.println(LINE + "\nThere is no/more than 1 event name provided.\nPlease use the format: event [task name] /from [start time] /to [end time]\n" + LINE);
            return;
        }

        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < eventName.length; i++) {
            taskName.append(eventName[i]);
            if (i < eventName.length - 1) {
                taskName.append(" ");
            }
        }

        Task eventTask = new EventTask(taskName.toString(), secondParse[1], firstParse[1]);
        this.tasks.add(eventTask);
        System.out.println(LINE + "\nTask added:\n   " + eventTask.toString() + "\nNow you have " + this.tasks.size() + " in the list.\n" + LINE);
    }

    public void parseDeadline(String[] strings) {
        if (strings.length != 2) {
            System.out.println(LINE + "\nThere is no/more than 1 deadline provided.\nPlease use the format: deadline [task name] /by [deadline]\n" + LINE);
            return;
        }

        String[] firstHalf = strings[0].split(" ");
        if (firstHalf.length < 2) {
            System.out.println(LINE + "\nThere is no task name provided.\nPlease use the format: deadline [task name] /by [deadline]\n" + LINE);
            return;
        }

        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < firstHalf.length; i++) {
            taskName.append(firstHalf[i]);
            if (i < firstHalf.length - 1) {
                taskName.append(" ");
            }
        }

        Task deadlineTask = new DeadlineTask(taskName.toString(), strings[1].trim());
        this.tasks.add(deadlineTask);
        System.out.println(LINE + "\nTask added:\n   " + deadlineTask.toString() + "\nNow you have " + this.tasks.size() + " in the list.\n" + LINE);
    }

    public void parseTodo(String[] strings) {
        if (strings.length < 2) {
            System.out.println(LINE + "\nThe description of a task cannot be empty\n" + LINE);
            return;
        }

        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < strings.length; i++) {
            taskName.append(strings[i]);
            if (i < strings.length - 1) {
                taskName.append(" ");
            }
        }

        Task todoTask = new TodoTask(taskName.toString());
        this.tasks.add(todoTask);
        System.out.println(LINE + "\nTask added:\n   " + todoTask.toString() + "\nNow you have " + this.tasks.size() + " in the list.\n" + LINE);
    }

    public void parseMark(String[] strings) {
        if (strings.length != 2) {
            System.out.println(LINE + "\nMark should be accompanied by a number\n" + LINE);
            return;
        }

        int num = 0;

        try {
            num = Integer.parseInt(strings[1]);
            num = num - 1;
        } catch (NumberFormatException e) {
            System.out.println(LINE + "\n" + "You did not input a number after your command. Please try again." + "\n" + LINE);
            return;
        }

        if (num < 0 || num >= tasks.size()) {
            System.out.println(LINE + "\n" + "The number you have inputted does not correspond to an item in the list. Please try again." + "\n" + LINE);
            return;
        }

        if (strings[0].equals("mark")) {
            Task t = tasks.get(num);
            t.markComplete();
            System.out.println(LINE + "\n" + "Marked task as done:" + "\n" + t.toString() + "\n" + LINE);
        } else if (strings[0].equals("unmark")) {
            Task t = tasks.get(num);
            t.markIncomplete();
            System.out.println(LINE + "\n" + "Marked task as undone:" + "\n" + t.toString() + "\n" + LINE);
        }
    }

    public static void main(String[] args) {
        Johnny johnny = new Johnny();
        System.out.println(johnny.greeting());
        johnny.read();
    }
}
