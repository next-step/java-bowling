package bowling.domain;

import bowling.exception.InvalidInputException;

public class Player {
    private static final String INVALID_USER_NAME_MESSAGE = "이름은 3글자를 초과할 수 없습니다.";

    private String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() > 3) {
            throw new InvalidInputException(INVALID_USER_NAME_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
