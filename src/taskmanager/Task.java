
package taskmanager;

/**
 * Represents an individual task in the Task Management System.
 *
 * @author Zain Ul Abdin
 */
public class Task {

    /** The title of the task. */
    private String title;

    /** The description of the task. */
    private String description;

    /** The current status of the task. */
    private String status;

    /**
     * Constructs a new task with Pending status.
     *
     * @param title the title of the task
     * @param description the description of the task
     */
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "Pending";
    }

    /**
     * Returns the task title.
     *
     * @return the task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Updates the task title.
     *
     * @param title the new task title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the task description.
     *
     * @param description the new task description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the current task status.
     *
     * @return the task status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the task status.
     *
     * @param status the new task status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
