package bowling.player;

import bowling.exception.BowlingBuildingException;

public class Player {

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.length() != 3) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_PLAYER_NAME);
        }
    }
}
