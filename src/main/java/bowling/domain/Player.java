package bowling.domain;

import bowling.exception.IllegalPlayerCriteriaException;

public class Player {
    private static final int PLAYER_NAME_LENGTH_STANDARD = 3;

    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNullAndEmpty(name);
        validatePlayerName(name);
    }

    private void validateNullAndEmpty(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalPlayerCriteriaException(name);
        }
    }

    private void validatePlayerName(String name) {
        if (name.length() != PLAYER_NAME_LENGTH_STANDARD) {
            throw new IllegalPlayerCriteriaException(name);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
