package exceptions;

public class CookieExceptions extends RuntimeException{
    public CookieExceptions(String message) {
        super(message);
        System.out.println(message);
    }
}
