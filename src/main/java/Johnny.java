import java.util.ArrayList;
import java.util.Scanner;

public class Johnny {

    private ArrayList<Task> tasks = new ArrayList<Task>();
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
            }
            else {
                System.out.println(LINE + "\n" + "added: " + input + "\n" + LINE);
                this.tasks.add(new Task(input));
            }
        }

        sc.close();
    }

    public void parseMark(String[] strings) {
        if (strings.length != 2) {
            System.out.println("Mark should be accompanied by a number");
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

        if (num <= 0 || num >= tasks.size()) {
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
