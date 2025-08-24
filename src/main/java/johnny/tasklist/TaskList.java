package johnny.tasklist;
import java.util.ArrayList;

import johnny.tasks.Task;

public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Class constructor. Takes in an ArrayList<Task> to wrap in this TaskList object.
     * @param tasks ArrayList<Task> passed into constructor
     * @see Task
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a task at a specified index in this TaskList object
     * @param index Index of task to get
     * @return Task gotten
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task deleted = this.tasks.get(index);
        this.tasks.remove(index);
        return deleted;
    }

    public void markComplete(int index) {
        this.tasks.get(index).markComplete();
    }

    public void markIncomplete(int index) {
        this.tasks.get(index).markIncomplete();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }

    public String getStoredString(int index) {
        return this.tasks.get(index).getStoredString();
    }

    public ArrayList<Task> findTasks(String subString) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.contains(subString)) {
                result.add(t);
            }
        }

        return result;
    }

    public String findTasksToString(String subString) {
        ArrayList<Task> matching = this.findTasks(subString);
        TaskList temp = new TaskList(matching);
        return temp.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

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

        return result.toString();
    }
}
