package utils;

public class Validator {
    public boolean isValidUserInput(String input) {
        return input != null && input.length() != 0 && input.matches("^(?!^\\s+$)[A-Za-zА-Яа-я\\s]+$");
    }
}
