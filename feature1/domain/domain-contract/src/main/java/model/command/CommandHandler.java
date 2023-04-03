package model.command;

/**
 * Interface for a command handler
 *
 * @param <T> the type of command handled by this handler
 * @param <R> the type of return of the handler
 */
public interface CommandHandler<R,T extends Command<R>> {

    /**
     * Handle the command passed in parameter
     *
     * @param command the command
     * @return a response of type R
     */
    R handle(T command);

    /**
     * Method to check if a given handler can handle a given command
     *
     * @param command the command to check against
     * @return true if the command can be handler by this handler, false otherwise
     */
    <C extends Command<?>> boolean canHandle(C command);
}
