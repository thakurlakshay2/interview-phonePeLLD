package exceptions;

public class NoViewAccessException extends RuntimeException{
    public NoViewAccessException() {
        super("No view access");
    }
}
