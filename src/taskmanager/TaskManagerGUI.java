
package taskmanager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Provides the graphical user interface for the Task Management System.
 *
 * @author Zain Ul Abdin
 */
public class TaskManagerGUI extends JFrame {

/** Manages task operations. */
private final TaskManager taskManager;

/** Input field for the task title. */
private JTextField titleField;

/** Input area for the task description. */
private JTextArea descriptionArea;

/** Selection box for task status. */
private JComboBox<String> statusComboBox;

/** Table displaying all tasks. */
private JTable taskTable;

/** Data model for the task table. */
private DefaultTableModel tableModel;

/** Label displaying application messages. */
private JLabel messageLabel;

    /**
     * Constructs and initializes the task management interface.
     */
    public TaskManagerGUI() {

        taskManager = new TaskManager();

        setTitle("Task Management System");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();
        refreshTable();
    }

    /**
     * Creates and arranges all graphical components.
     */
    private void createGUI() {

        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel(
                "TASK MANAGEMENT SYSTEM", SwingConstants.CENTER);

        heading.setFont(
                new java.awt.Font("Arial", java.awt.Font.BOLD, 24));

        add(heading, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        titleField = new JTextField();

        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        statusComboBox = new JComboBox<>(
                new String[]{"Pending", "In Progress", "Completed"});

        inputPanel.add(new JLabel("Task Title:"));
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(new JScrollPane(descriptionArea));

        inputPanel.add(new JLabel("Task Status:"));
        inputPanel.add(statusComboBox);

        // Table
        tableModel = new DefaultTableModel(
                new String[]{"Title", "Description", "Status"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        taskTable = new JTable(tableModel);

        taskTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScrollPane = new JScrollPane(taskTable);

        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JButton addButton = new JButton("Add Task");
        JButton updateButton = new JButton("Update Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton statusButton = new JButton("Update Status");
        JButton clearButton = new JButton("Clear");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(statusButton);
        buttonPanel.add(clearButton);

        // Message label
        messageLabel = new JLabel(
                "Welcome! Add your first task.",
                SwingConstants.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(messageLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addTask());

        updateButton.addActionListener(e -> updateTask());

        deleteButton.addActionListener(e -> deleteTask());

        statusButton.addActionListener(e -> updateStatus());

        clearButton.addActionListener(e -> clearFields());

        // Populate fields when a task is selected
        taskTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int selectedRow = taskTable.getSelectedRow();

                if (selectedRow >= 0) {

                    titleField.setText(
                            tableModel.getValueAt(selectedRow, 0).toString());

                    descriptionArea.setText(
                            tableModel.getValueAt(selectedRow, 1).toString());

                    statusComboBox.setSelectedItem(
                            tableModel.getValueAt(selectedRow, 2).toString());
                }
            }
        });
    }

    /**
     * Adds a new task after validating the title.
     */
    private void addTask() {

        try {

            String title = titleField.getText();
            String description = descriptionArea.getText();

            taskManager.addTask(title, description);

            refreshTable();
            clearFields();

            showMessage("Task added successfully!");

        } catch (IllegalArgumentException ex) {

            showMessage(ex.getMessage());
        }
    }

    /**
     * Updates the selected task's title and description.
     */
    private void updateTask() {

        int selectedRow = taskTable.getSelectedRow();

        if (selectedRow == -1) {

            showMessage("Please select a task to update.");
            return;
        }

        try {

            taskManager.updateTask(
                    selectedRow,
                    titleField.getText(),
                    descriptionArea.getText());

            refreshTable();
            clearFields();

            showMessage("Task updated successfully!");

        } catch (IllegalArgumentException
                | IndexOutOfBoundsException ex) {

            showMessage(ex.getMessage());
        }
    }

    /**
     * Deletes the selected task.
     */
    private void deleteTask() {

        int selectedRow = taskTable.getSelectedRow();

        if (selectedRow == -1) {

            showMessage("Please select a task to delete.");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this task?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            taskManager.deleteTask(selectedRow);

            clearFields();
            refreshTable();

            if (taskManager.getAllTasks().isEmpty()) {

                showMessage(
                        "No tasks available. Add your first task.");

            } else {

                showMessage("Task deleted successfully!");
            }

        } catch (IndexOutOfBoundsException ex) {

            showMessage(ex.getMessage());
        }
    }

    /**
     * Changes the status of the selected task.
     */
    private void updateStatus() {

        int selectedRow = taskTable.getSelectedRow();

        if (selectedRow == -1) {

            showMessage("Please select a task first.");
            return;
        }

        try {

            String status =
                    (String) statusComboBox.getSelectedItem();

            taskManager.setTaskStatus(selectedRow, status);

            refreshTable();
            clearFields();

            showMessage("Task status updated successfully!");

        } catch (IllegalArgumentException
                | IndexOutOfBoundsException ex) {

            showMessage(ex.getMessage());
        }
    }

    /**
     * Refreshes the task table with the current task collection.
     */
    private void refreshTable() {

        tableModel.setRowCount(0);

        List<Task> tasks = taskManager.getAllTasks();

        for (Task task : tasks) {

            tableModel.addRow(new Object[]{
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
            });
        }

        if (tasks.isEmpty()) {

            showMessage(
                    "No tasks available. Add your first task.");
        }
    }

    /**
     * Clears all input fields and table selection.
     */
    private void clearFields() {

        titleField.setText("");
        descriptionArea.setText("");
        statusComboBox.setSelectedIndex(0);

        taskTable.clearSelection();
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to display
     */
    private void showMessage(String message) {

        messageLabel.setText(message);
    }

    /**
     * Starts the Task Management System application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TaskManagerGUI gui = new TaskManagerGUI();

            gui.setVisible(true);
        });
    }
}
