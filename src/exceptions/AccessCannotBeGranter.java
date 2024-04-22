package exceptions;

public class AccessCannotBeGranter extends RuntimeException{

    public AccessCannotBeGranter() {
        super("this access cannot be granted");
    }
}
