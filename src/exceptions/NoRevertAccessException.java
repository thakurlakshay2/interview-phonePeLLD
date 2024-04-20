package exceptions;

public class NoRevertAccessException extends RuntimeException{
    public NoRevertAccessException() {
        super(" Revert access given to you");
    }
}
