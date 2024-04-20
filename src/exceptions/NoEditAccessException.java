package exceptions;

public class NoEditAccessException extends  RuntimeException{
    public NoEditAccessException() {
        super("No edit access given to you");
    }
}
