package bowling.player.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player user = (Player) o;
        return Objects.equals(playerName, user.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

}
