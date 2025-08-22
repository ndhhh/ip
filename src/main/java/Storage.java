import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public ArrayList<Task> load() {
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
                        tasks.add(new DeadlineTask(taskName, completed, deadline));
                        break;
                    
                    case "E":
                        String start = strings[3];
                        String end = strings[4];
                        tasks.add(new EventTask(taskName, completed, start, end));
                        break;
                }
            }
            
            sc.close();

        } catch (IOException | NoSuchElementException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } 
        
        return tasks;
    } 

    public void save(ArrayList<Task> tasks) {
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
                Task current = tasks.get(i);
                String storedTask = current.getStoredString();
                fw.write(storedTask + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to saved task file: " + e.getMessage());
        }
    }
}

