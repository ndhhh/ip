package johnny.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import johnny.parser.Parser;
import johnny.tasklist.TaskList;
import johnny.tasks.DeadlineTask;
import johnny.tasks.EventTask;
import johnny.tasks.Task;
import johnny.tasks.TodoTask;
import johnny.ui.Ui;

/**
 * A class used in storing and loading tasks from the save file.
 */
public class Storage {
    // Done
    private final String filePath;

    /**
     * Constructs a new instance of Storage using the specified String, which is a
     * file path.
     * 
     * @param filePath String to the path of the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of Task that is populated from reading the filePath of
     * the storage object.
     * 
     * The ui argument refers to a Ui object that prints any errors from parsing the
     * text file.
     * 
     * @param ui Ui object that prints any errors from parsing the text file / text
     *           interactions with the user
     * @return an ArrayList<Task> that is passed into a TaskList
     * @see TaskList
     */
    public ArrayList<Task> load(Ui ui) {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        if (!file.exists()) {
            this.createNewFile(ui, file);
            return tasks; // return empty arraylist as no initial file was present
        }

        this.parseTasks(ui, file, tasks);

        return tasks;
    }

    private void createNewFile(Ui ui, File file) {
        // if the file doesn't exist, i.e. on first run of program,
        // create a new file
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (SecurityException | IOException e) {
            System.out.println("Error creating task file: " + e.getMessage());
        }
    }

    private void parseTasks(Ui ui, File file, ArrayList<Task> tasks) {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                // While there are still lines to read, parse the lines into tasks
                String nextLine = sc.nextLine();
                String[] strings = nextLine.split("\\|");
                this.addTasks(ui, tasks, strings);
            }

            sc.close();

        } catch (IOException | NoSuchElementException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void addTasks(Ui ui, ArrayList<Task> tasks, String[] strings) {
        String typeOfTask = strings[0];
        boolean completed = strings[1].equals("1");
        String taskName = strings[2];

        switch (typeOfTask) {
            case "T":
                tasks.add(new TodoTask(taskName, completed));
                break;

            case "D":
                String deadline = strings[3];
                LocalDate date = Parser.parseDate(deadline, ui);
                if (date != null) {
                    tasks.add(new DeadlineTask(taskName, completed, date));
                }
                break;

            case "E":
                String start = strings[3];
                LocalDateTime startDateTime = Parser.parseDateTime(start, ui);
                String end = strings[4];
                LocalTime endTime = Parser.parseTime(end, ui);
                if (startDateTime != null && endTime != null) {
                    tasks.add(new EventTask(taskName, completed, startDateTime, endTime));
                }
                break;
        }
    }

    /**
     * Takes a TaskList object and writes the Task objects as text
     * form into the filePath of the storage object.
     * 
     * @param tasks A TaskList object that provides the tasks to be saved into the
     *              file path
     * @see TaskList
     */
    public void save(TaskList tasks) {
        File file = new File(this.filePath);

        if (!file.exists()) {
            // if the file doesn't exist, i.e. on first run of program,
            // create a new file
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (SecurityException | IOException e) {
                System.out.println("Error creating task file: " + e.getMessage());
            }
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < tasks.size(); i++) {
                String storedTask = tasks.getStoredString(i);
                if (i == tasks.size() - 1) {
                    fw.write(storedTask);
                } else {
                    fw.write(storedTask + "\n");
                }
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to saved task file: " + e.getMessage());
        }
    }
}
