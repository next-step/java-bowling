package bowling.domain;

import java.util.Objects;

import static bowling.common.ExceptionMessage.PLAYER_NAME_LENGTH;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;

        if (name.length() > 3) {
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String name() {
        return name;
    }
}
