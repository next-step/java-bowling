package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    public static final String NAME_FORMAT = "^[a-zA-Z]{3}";
    private final String name;

    public Name(String name) {
        validateFormat(name);
        this.name = name;
    }

    private void validateFormat(String name) {
        if(Objects.isNull(name) || !Pattern.matches(NAME_FORMAT, name)) {
            throw new BowlingGameException(ErrorMessage.WRONG_NAME_FORMAT);
        }
    }

    public String getName() {
        return name;
    }
}
