
package taskmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of tasks and provides
 * operations to add, view, update and delete tasks.
 *
 * @author Zain Ul Abdin
 */
public class TaskManager {

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskManager.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the collection.
     *
     * @param title the task title
     * @param description the task description
     * @throws IllegalArgumentException if title is empty
     */
    public void addTask(String title, String description) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Task title cannot be empty.");
        }

        Task task = new Task(title.trim(),
                description == null ? "" : description.trim());

        tasks.add(task);
    }

    /**
     * Returns all tasks.
     *
     * @return a list containing all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Updates an existing task.
     *
     * @param index the position of the task
     * @param title the new task title
     * @param description the new task description
     * @throws IllegalArgumentException if title is empty
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void updateTask(int index, String title,
                           String description) {

        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Task does not exist.");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Task title cannot be empty.");
        }

        Task task = tasks.get(index);

        task.setTitle(title.trim());
        task.setDescription(
                description == null ? "" : description.trim());
    }

    /**
     * Deletes an existing task.
     *
     * @param index the position of the task
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void deleteTask(int index) {

        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Task does not exist.");
        }

        tasks.remove(index);
    }

    /**
     * Changes the status of an existing task.
     *
     * @param index the position of the task
     * @param status the new task status
     * @throws IndexOutOfBoundsException if index is invalid
     * @throws IllegalArgumentException if status is invalid
     */
    public void setTaskStatus(int index, String status) {

        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "Task does not exist.");
        }

        if (!"Pending".equals(status)
                && !"In Progress".equals(status)
                && !"Completed".equals(status)) {

            throw new IllegalArgumentException(
                    "Invalid task status.");
        }

        tasks.get(index).setStatus(status);
    }
}

