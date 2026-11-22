
# Task Management System

## 1. Project Description

The Task Management System is a Java-based desktop application developed as part of the Software Construction course.

It provides a graphical user interface (GUI) to manage daily tasks efficiently.

Users can add, view, update, delete tasks and change their task status.

## 2. Features

- Add new tasks.
- Update existing tasks.
- Delete tasks with confirmation.
- Update task status.
- Display tasks in a table.
- Validate empty task titles.
- Handle task selection errors.
- Display appropriate messages when the task list is empty.

## 3. Technologies Used

- Java 21
- Java Swing (GUI)
- Visual Studio Code
- Git and GitHub

## 4. Project Structure

```text
TaskManagementSystem/
│
├── .vscode/
├── .gitignore
├── README.md
├── src/
│   ├── App.java
│   └── taskmanager/
│       ├── Task.java
│       ├── TaskManager.java
│       └── TaskManagerGUI.java
│
└── out/
```

## 5. Requirements

- Java Development Kit (JDK) 21 or later.
- Visual Studio Code (or another Java IDE).

## 6. How to Compile and Run

Open the terminal in the project root directory.

Compile the project:

```powershell
javac -d out src/taskmanager/*.java
```

Run the application:

```powershell
java -cp out taskmanager.TaskManagerGUI
```

## 7. Git Version Control

Git is used to track project changes and maintain version history.

The project includes:
- Git repository initialization.
- Meaningful commits.
- Source code tracking.
- Git status, log and diff operations.

## 8. Author

**Zain Ul Abdin**

Software Construction Course
