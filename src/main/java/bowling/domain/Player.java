package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    public static final String NAME_FORMAT = "^[a-zA-Z]{3}";
    private final String name;

    public Player(String name) {
        validateFormat(name);
        this.name = name;
    }

    private void validateFormat(String name) {
        if(Objects.isNull(name) || !Pattern.matches(NAME_FORMAT, name)) {
            throw new BowlingGameException(ErrorMessage.WRONG_NAME_FORMAT);
        }
    }
}
