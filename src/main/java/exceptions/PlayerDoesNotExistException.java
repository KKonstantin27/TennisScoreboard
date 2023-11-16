package exceptions;

import javax.servlet.ServletException;

public class PlayerDoesNotExistException extends ServletException {
    public PlayerDoesNotExistException(String message) {
        super(message);
    }
}

