package exceptions;

public class cookieExceptions extends Throwable{
    public cookieExceptions(String message) {
        super(message);
        System.out.println(message);
    }
}
