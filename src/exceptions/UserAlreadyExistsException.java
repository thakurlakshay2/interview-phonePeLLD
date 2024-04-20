package exceptions;

public class UserAlreadyExistsException extends  RuntimeException{
    public UserAlreadyExistsException() {
        super("This user Already exists");
    }
}
