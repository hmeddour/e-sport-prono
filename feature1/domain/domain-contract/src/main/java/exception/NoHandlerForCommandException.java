package exception;

import model.command.Command;

public class NoHandlerForCommandException extends RuntimeException{

    public NoHandlerForCommandException(Command command) {

    }
}
