package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManagement {
    private File file;
    private File filedirectory;
    private String path;
    private String directory;

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

    public void save(TaskStorage taskStorage) {
        try {
            FileWriter fw = new FileWriter(this.path);
            for (int i = 0; i < taskStorage.noTasks(); i++) {
                fw.write(taskStorage.getTask(i).toString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
