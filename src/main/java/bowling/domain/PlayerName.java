package bowling.domain;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName that = (PlayerName) o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

    @Override
    public String toString() {
        return this.playerName;
    }
}
