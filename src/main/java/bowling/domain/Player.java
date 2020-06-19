package bowling.domain;

public class Player {
    private static final int MANDATORY_NAME_LENGTH = 3;

    private static String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.length() != MANDATORY_NAME_LENGTH) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_PLAYER_NAME);
        }
    }
}
