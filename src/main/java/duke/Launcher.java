package duke;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * duke.task.A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}