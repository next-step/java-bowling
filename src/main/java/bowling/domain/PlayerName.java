package bowling.domain;

import static bowling.util.Const.MAX_NAME_LENGTH;

public class PlayerName {
    private String playerName;

    public PlayerName(String playerName) {
        if (playerName.length() != MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Player name should be 3 letter but, : " + playerName);
        }
            this.playerName = playerName;
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}
