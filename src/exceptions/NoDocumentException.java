package exceptions;

public class NoDocumentException extends  RuntimeException{
    public NoDocumentException() {
        super("No document Found");

    }
}
