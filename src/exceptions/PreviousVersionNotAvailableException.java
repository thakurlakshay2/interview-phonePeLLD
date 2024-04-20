package exceptions;

public class PreviousVersionNotAvailableException extends RuntimeException{
    public PreviousVersionNotAvailableException() {
        super("No previous version available for this document");
    }
}
