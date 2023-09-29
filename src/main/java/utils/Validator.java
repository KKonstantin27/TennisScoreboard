package utils;

public class Validator {
    public boolean isValidUserInput(String input) {
        boolean isValidUserInput = true;
        String[] name = input.split(" ");
        if (input == null || input.length() == 0 || !input.matches("^(?!^\\s+$)[A-Za-zА-Яа-я\\s]+$")) {
            isValidUserInput = false;
        } else if (name.length > 2 || name.length == 0) {
            isValidUserInput = false;
        } else {
            for (int i = 0; i < name.length; i++) {
                if (name[i].length() > 10) {
                    isValidUserInput = false;
                }
            }
        }
        return isValidUserInput;
    }
}
