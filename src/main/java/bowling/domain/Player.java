package bowling.domain;

import bowling.exception.InvalidPlayerNameLengthException;

public class Player {

    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        validateNameLength(name);
        this.name = name;
    }

    public String name() {
        return name;
    }

    private void validateNameLength(String name) {
        int nameLength = name.length();
        if (nameLength < MIN_NAME_LENGTH || nameLength > MAX_NAME_LENGTH) {
            throw new InvalidPlayerNameLengthException(nameLength);
        }
    }

}
