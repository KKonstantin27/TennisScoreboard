package exceptions;

import javax.servlet.ServletException;

public class InvalidNameFormatForCreationException extends ServletException {
    public InvalidNameFormatForCreationException(String message) {
        super(message);
    }
}

