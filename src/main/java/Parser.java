import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static final String LINE = "__________________________________________________";

    public static Command read(String input, Storage storage, TaskList tasks, Ui ui) {
        if (input == null) {
            return null;
        } else if (input.equals("bye")){
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ") || input.startsWith("unmark ")) {
            return Parser.parseMark(input, ui);
        } else if (input.startsWith("todo ")) {
            return Parser.parseTodo(input, ui);
        } else if (input.startsWith("deadline ")) {
            return Parser.parseDeadline(input, ui);
        } else if (input.startsWith("event ")) {
            return Parser.parseEvent(input, ui);
        } else if (input.startsWith("delete ")) {
            return Parser.parseDelete(input, ui);
        } else {
            System.out.println(LINE + "\n" + "Invalid command." + "\n" + LINE);
            return null;
        }
    }

    public static Command parseDelete(String input, Ui ui) {
        String[] strings = input.split(" ");
        if (strings.length != 2) {
            ui.printNoNumberError();
            return null;
        }

        int num = 0;

        try {
            num = Integer.parseInt(strings[1]);
            num = num - 1;
        } catch (NumberFormatException e) {
            ui.printInvalidNumberError();
            return null;
        }
        
        return new DeleteCommand(num);
    }

    public static Command parseEvent(String input, Ui ui) {
        String[] firstParse = input.split("/to ");
        if (firstParse.length != 2) {
            System.out.println(LINE + "\nThere is no/more than 1 event end time provided.\nPlease use the format: event [task name] /from [start time] /to [end time]\n" + LINE);
            return null;
        }

        String[] secondParse = firstParse[0].split("/from ");
        if (secondParse.length != 2) {
            System.out.println(LINE + "\nThere is no/more than 1 event start time provided.\nPlease use the format: event [task name] /from [start time] /to [end time]\n" + LINE);
            return null;
        }

        String[] eventName = secondParse[0].split(" ");
        if (eventName.length < 2) {
            System.out.println(LINE + "\nThere is no/more than 1 event name provided.\nPlease use the format: event [task name] /from [start time] /to [end time]\n" + LINE);
            return null;
        }

        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < eventName.length; i++) {
            taskName.append(eventName[i]);
            if (i < eventName.length - 1) {
                taskName.append(" ");
            }
        }

        try {
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime start = LocalDateTime.parse(secondParse[1].trim(), dtFormatter);
            LocalTime end = LocalTime.parse(firstParse[1].trim(), tFormatter);
            return new EventCommand(taskName.toString(), start, end);
        } catch (DateTimeException e) {
            ui.printDateTimeException(e);
            return null;
        }
    }

    public static Command parseDeadline(String input, Ui ui) {
        String[] strings = input.split("/by ");

        if (strings.length != 2) {
            ui.printDeadlineError();
            return null;
        }

        String[] firstHalf = strings[0].split(" ");
        if (firstHalf.length < 2) {
            ui.printNoTaskNameError();
            return null;
        }

        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < firstHalf.length; i++) {
            taskName.append(firstHalf[i]);
            if (i < firstHalf.length - 1) {
                taskName.append(" ");
            }
        }

        String[] secondHalf = strings[1].trim().split("/");
        if (secondHalf.length != 3) {
            ui.printDateError();
            return null;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate deadline = LocalDate.parse(strings[1].trim(), formatter);
            return new DeadlineCommand(taskName.toString(), deadline);
        } catch (DateTimeException e) {
            ui.printDateTimeException(e);
            return null;
        }
    }

    public static Command parseTodo(String input, Ui ui) {
        String[] strings = input.split(" ");

        if (strings.length < 2) {
            ui.printNoTaskNameError();
            return null;
        }

        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < strings.length; i++) {
            taskName.append(strings[i]);
            if (i < strings.length - 1) {
                taskName.append(" ");
            }
        }

        return new TodoCommand(taskName.toString());
    }

    public static Command parseMark(String input, Ui ui) {
        String[] strings = input.split(" ");

        if (strings.length != 2) {
            ui.printNoNumberError();
            return null;
        }

        int num = 0;

        try {
            num = Integer.parseInt(strings[1]);
            num = num - 1;
        } catch (NumberFormatException e) {
            ui.printInvalidNumberError();
            return null;
        }

        if (strings[0].equals("mark")) {
            return new MarkCommand(num);
        } else {
            return new UnmarkCommand(num);
        }
    }

    public static LocalDate parseDate(String input, Ui ui) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(input, formatter);
            return date;
        } catch (DateTimeException e) {
            ui.printDateTimeException(e);
            return null;
        }
    }

    public static LocalDateTime parseDateTime(String input, Ui ui) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime;
        } catch (DateTimeException e) {
            ui.printDateTimeException(e);
            return null;
        }
    }

    public static LocalTime parseTime(String input, Ui ui) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime time = LocalTime.parse(input, formatter);
            return time;
        } catch (DateTimeException e) {
            ui.printDateTimeException(e);
            return null;
        }
    }
}
