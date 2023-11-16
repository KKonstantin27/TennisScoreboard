package exceptions;

import javax.servlet.ServletException;

public class InvalidNameFormatForSearchException extends ServletException {
    public InvalidNameFormatForSearchException(String message) {
        super(message);
    }
}

