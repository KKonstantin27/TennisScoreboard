package exceptions;

import javax.servlet.ServletException;

public class SamePlayersException extends ServletException {
    public SamePlayersException(String message) {
        super(message);
    }
}
