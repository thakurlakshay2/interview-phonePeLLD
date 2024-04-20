package exceptions;

public class PasswordIncorrect extends RuntimeException{
    public PasswordIncorrect() {
        super("Password incorrect");
    }
}
