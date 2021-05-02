package bowling.domain;

import bowling.domain.exception.NameLengthException;

import java.util.regex.Pattern;

public class PlayerName {
    private final static String NAME_REGEX = "^[a-zA-Z]{3}$";
    private final static Pattern PATTERN_NAME = Pattern.compile(NAME_REGEX);
    private final String name;

    public PlayerName(String name) {
        validateName(name);
        this.name = name;
    }

    public String name(){
        return name;
    }

    private void validateName(String name) {
        if (!PATTERN_NAME.matcher(name).matches()) {
            throw new NameLengthException();
        }
    }
}
