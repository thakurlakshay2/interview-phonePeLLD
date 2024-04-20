package exceptions;

public class DocumentAlreadyExistsException extends RuntimeException{
    public DocumentAlreadyExistsException() {
        super("Document Already exists");
    }
}
