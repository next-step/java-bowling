package bowling.player;

import java.util.regex.Pattern;

public class Player {
    private static final Pattern PATTERN = Pattern.compile("\\W");
    private static final int NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        validateName(name);

        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private void validateName(String name) {
        if (isNotValidName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    private boolean isNotValidName(String name) {
        return (PATTERN.matcher(name).find()) || (name.length() != NAME_LENGTH);
    }
}
