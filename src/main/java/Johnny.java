import java.util.Scanner;

public class Johnny {

    public static String greeting() {
        String line = "__________________________________________________";
        String name = "Johnny";
        
        return line + "\n" + "Hello! I'm " + name + "\nWhat can I do for you?\n" + line;
    }

    public static void read() {
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
            } else {
                System.out.println(line + "\n" + input + "\n" + line);
            }
        }

        sc.close();

    }

    public static void main(String[] args) {
        System.out.println(Johnny.greeting());
        Johnny.read();
    }
}
