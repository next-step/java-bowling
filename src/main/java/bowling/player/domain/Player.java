package bowling.player.domain;

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

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return playerName;
    }

    public static Player of(String playerName) {
        return new Player(playerName);
    }

}
