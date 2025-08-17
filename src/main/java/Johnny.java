import java.util.ArrayList;
import java.util.Scanner;

public class Johnny {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Johnny() {

    }

    public String greeting() {
        String line = "__________________________________________________";
        String name = "Johnny";
        
        return line + "\n" + "Hello! I'm " + name + "\nWhat can I do for you?\n" + line;
    }

    public String tasksToString() {
        String line = "__________________________________________________";
        StringBuilder result = new StringBuilder(line);
        result.append("\n");

        if (this.tasks.size() == 0) {
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

        return result.append(line).toString();
    }

    public void read() {
        String line = "__________________________________________________";
        Scanner sc = new Scanner(System.in);
        String input = "";

        while (true)
        {
            input = sc.nextLine();
            if (input.equals("bye"))
            {
                System.out.println(line + "\nBye. Hope to see you again!\n" + line);
                break;
            } else if (input.equals("list")) {
                System.out.println(this.tasksToString());
            } else {
                System.out.println(line + "\n" + "added: " + input + "\n" + line);
                this.tasks.add(new Task(input));
            }
        }

        sc.close();

    }

    public static void main(String[] args) {
        Johnny johnny = new Johnny();
        System.out.println(johnny.greeting());
        johnny.read();
    }
}
