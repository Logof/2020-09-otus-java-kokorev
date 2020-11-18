package exceptions;

public class cookieExceptions extends RuntimeException{
    public cookieExceptions(String message) {
        super(message);
        System.out.println(message);
    }
}
