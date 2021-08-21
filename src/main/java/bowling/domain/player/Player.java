package bowling.domain.player;

import bowling.exception.IllegalPayerNameException;

import java.util.regex.Pattern;

public class Player {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{3}$");

    private final String name;

    private Player(String name) {
        validate(name);
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    private void validate(String name) {
        if (!NAME_PATTERN.matcher(name).find()) {
            throw new IllegalPayerNameException(name);
        }
    }
}
