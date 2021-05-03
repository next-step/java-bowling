package bowling.domain.player;

import java.util.Objects;

public class PlayerName {
    private static final int MAX_NAME_LENGTH = 3;

    private final String playerName;

    public static PlayerName of(String playerName) {
        return new PlayerName(playerName);
    }

    PlayerName(String playerName) {
        this.playerName = validate(playerName.trim());
    }

    private String validate(String name) {
        if (name.length() > MAX_NAME_LENGTH || name.isEmpty()) {
            throw new IllegalArgumentException("Player 이름은 3글자 이하여야 합니다.");
        }
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlayerName that = (PlayerName)o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

    @Override
    public String toString() {
        return playerName;
    }
}
