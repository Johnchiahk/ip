package duke;

/**
 * Class to represent exceptions specific to duke.Duke.
 */
public class DukeException extends Exception {

    public DukeException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public DukeException(String message) {
        super(message);
    }
}
