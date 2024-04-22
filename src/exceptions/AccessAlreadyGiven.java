package exceptions;

public class AccessAlreadyGiven extends RuntimeException{
    public AccessAlreadyGiven() {
        super("access already given");
    }
}
