package exception;

public class NullCommandException extends RuntimeException{

    @Override
    public String getMessage() {
        return "The provided input was null";
    }
}
