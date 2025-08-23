import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Storage {
    // Done
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public ArrayList<Task> load(Ui ui) {
        ArrayList<Task> tasks = new ArrayList<>();
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

            return tasks; // return empty arraylist as no initial file was present
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                // While there are still lines to read, parse the lines into tasks
                String nextLine = sc.nextLine();
                String[] strings = nextLine.split("\\|");
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
                        if (startDateTime != null && endTime!= null) {
                            tasks.add(new EventTask(taskName, completed, startDateTime, endTime));
                        }
                        break;
                }
            }
            
            sc.close();

        } catch (IOException | NoSuchElementException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } 
        
        return tasks;
    } 

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

