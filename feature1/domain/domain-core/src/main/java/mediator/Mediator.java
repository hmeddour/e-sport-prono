package mediator;

import model.command.Command;

/**
 * Interface for the mediator
 */
public interface Mediator {
    /**
     * Excecute the given command
     *
     * @param command the command to execute
     * @param <T>     the type of command
     * @param <R>     the type of return
     * @return the result of the execution
     */
    <R, T extends Command<R>> R execute(T command);
}
