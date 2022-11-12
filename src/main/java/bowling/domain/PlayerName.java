package bowling.domain;

public class PlayerName {
    public static final int NAME_LENGTH = 3;

    private final String playerName;

    public PlayerName(String playerName) {
        if(!isValidNameLength(playerName)) {
            throw new IllegalArgumentException("player name length must be 3");
        }
        this.playerName = playerName;
    }

    private boolean isValidNameLength(String playerName) {
        return playerName.length() == NAME_LENGTH;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
