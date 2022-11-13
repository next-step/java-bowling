package bowling.domain;

public class PlayerName {
    private static final int NAME_LENGTH = 3;
    private static final String ENGLISH_REGEX = "^[a-zA-Z]*$";

    private final String playerName;

    public PlayerName(String playerName) {
        if(!isValidNameLength(playerName)) {
            throw new IllegalArgumentException("player name length must be 3");
        }
        if(!isEnglish(playerName)) {
            throw new IllegalArgumentException("player name must be english");
        }
        this.playerName = playerName;
    }

    private boolean isValidNameLength(String playerName) {
        return playerName.length() == NAME_LENGTH;
    }
    private boolean isEnglish(String playerName) {
        return playerName.matches(ENGLISH_REGEX);
    }

    @Override
    public String toString() {
        return playerName;
    }
}
