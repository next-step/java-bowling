package bowling.domain;

public class Player {

    private static final int MAX_LENGTH_OF_NAME = 3;

    private final String playerName;

    public Player(String playerName) {
        validateName(playerName);
        this.playerName = playerName;
    }

    private void validateName(String playerName) {
        if (playerName.length() != MAX_LENGTH_OF_NAME) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return playerName;
    }

}
