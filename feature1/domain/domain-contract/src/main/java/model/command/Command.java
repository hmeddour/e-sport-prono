package model.command;

/**
 * Marker interface for a command object
 */
public interface Command<R> {

    /**o
     * Method to check if a given command is valid
     *
     * @return true if the command is valid, false otherwise
     */
    boolean isValid();

    /**
     * The class of the result for this command
     *
     * @return the class of the result for this command
     */
    Class<R> resultClass();
}
