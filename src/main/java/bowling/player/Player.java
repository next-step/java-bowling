package bowling.player;

import bowling.exception.BowlingBuildingException;

public class Player {
    private static final int REQUIRED_NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.length() != REQUIRED_NAME_LENGTH) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_PLAYER_NAME);
        }
    }

    public String getName() {
        return name;
    }
}
