package exceptions;

public class NoDeleteAccessException extends RuntimeException{
    public NoDeleteAccessException() {
        super("Delete Access not granted");
    }
}
