package bowling.domain.player;

import bowling.util.StringUtils;

public class Player {
    private static final int LIMIT_LENGTH_OF_NAME = 3;

    private final String name;

    public Player(String name) {
        validate(name);

        this.name = name;
    }

    private void validate(String name) {
        if (StringUtils.isEmpty(name) || name.length() != LIMIT_LENGTH_OF_NAME) {
            throw new IllegalStateException("Name must be " + LIMIT_LENGTH_OF_NAME + " length chars");
        }
    }

    public static Player from(String name) {
        return new Player(name);
    }
}
