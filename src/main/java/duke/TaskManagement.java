package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Analogous to Storage suggested by the module coordinators.
 * Stores all the tasks of a Duke Object.
 */
public class TaskManagement {
    private File file;
    private File filedirectory;
    private String path;
    private String directory;

    /**
     * Constructs a TaskManagement object. By default, the file which contains
     * the tasks of the TaskManagement object will be placed in ./data/tasks.txt.
     */
    public TaskManagement() {
        this.directory = "./data/";
        this.path = this.directory + "tasks.txt";
        this.file = new File(this.path);
        this.filedirectory = new File(this.directory);
        try {
            if (!this.filedirectory.exists()) { //handle case where folder does not exist
                filedirectory.mkdirs();
            }
            if (!this.file.exists()) { //handle case where file does not exist
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructs a TaskManagement object.
     *
     * The file which contains the tasks of the TaskManagement object
     * will be placed in the filepath given.
     *
     * @param filepath The filepath given.
     * @throws DukeException If invalid filepath input is given.
     */
    public TaskManagement(String filepath) throws DukeException {
        if (!filepath.substring(0, 2).equals("./") || filepath.length() <= 2) {
            throw new DukeException();
        }
        String rem = filepath.substring(2);
        String[] inp = rem.split("/");
        if (inp.length != 2) {
            throw new DukeException();
        }
        this.directory = "./" + inp[0] + "/";
        this.path = filepath;
        this.file = new File(this.path);
        this.filedirectory = new File(this.directory);
        try {
            if (!this.filedirectory.exists()) { //handle case where folder does not exist
                filedirectory.mkdirs();
            }
            if (!this.file.exists()) { //handle case where file does not exist
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the tasks of the TaskStorage object into the TaskManagement object.
     * The file will be updated.
     *
     * @param taskStorage The TaskStorage object given.
     */
    public void save(TaskStorage taskStorage) {
        try {
            FileWriter fw = new FileWriter(this.path);
            for (Task task : taskStorage.getTasks()) {
                fw.write(task.encode() + "\n");
            }
//            for (int i = 0; i < taskStorage.noTasks(); i++) {
//                fw.write(taskStorage.getTask(i).toString());
//                fw.write(System.lineSeparator());
//            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Task> retrieve() {
        Scanner scanner = null;
        List<Task> taskList = new ArrayList<>();
        try {
            scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String encoded = scanner.nextLine();
                taskList.add(this.decodeTask(encoded));
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
        return taskList;
    }

    private Task decodeTask(String task) {
        System.out.println(task);
        String[] components = task.split(" ### ");
        String command = components[0];
        String toMark = components[1];
        String des = components[2];
        Task decoded = null;
        switch (command) {
        case "todo":
            decoded = new Todo(des);
            break;
        case "deadline":
            decoded = new Deadline(des, components[3]);
            break;
        case "event":
            decoded = new Event(des, components[3], components[4]);
            break;
        default:
            assert false : "Saved file may be corrupted";
            break;
        }
        if (toMark.equals("true")) {
            decoded.markAsDone();
        }
        return decoded;
    }

}
