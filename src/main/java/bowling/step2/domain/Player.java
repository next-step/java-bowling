package bowling.step2.domain;

import bowling.step2.exception.PlayerNameEmptyException;
import bowling.step2.exception.PlayerNameMaximumException;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private static final Map<String, Player> FACTORY = new HashMap<>();
    private final String value;

    private Player(String value) {
        this.value = value;
    }

    public static Player valueOf (String value) {
        validateEmpty(value);
        validateMaximumLength(value);
        return FACTORY.computeIfAbsent(value, Player::new);
    }

    private static void validateEmpty (String value) {
        if (value == null || value.trim().equals("")) {
            throw new PlayerNameEmptyException();
        }
    }

    private static void validateMaximumLength (String value) {
        if (value.length() > 3) {
            throw new PlayerNameMaximumException();
        }
    }

    @Override
    public String toString () {
        return value;
    }
}