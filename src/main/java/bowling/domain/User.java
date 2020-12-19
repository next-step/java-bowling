package bowling.domain;

import bowling.bowlingexception.InvalidNameFormatException;

public class User {

    private final String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.length() != 3 || !hasOnlyLetters(name)) {
            throw new InvalidNameFormatException();
        }
    }

    private boolean hasOnlyLetters(String input) {
        int numChar = 0;

        for (int i = 0; i < input.length(); i++) {
            numChar += Character.isLetter(input.indexOf(i)) ? 1 : 0;
        }

        return numChar == input.length();
    }
}
