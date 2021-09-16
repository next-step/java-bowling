package bowling.domain;

import bowling.exception.InvalidPlayersException;

import java.util.Objects;

public class Player {
    private final String player;

    private Player(String player) {
        validate(player);
        this.player = player;
    }

    private void validate(String player) {
        if (player.length() > 3) {
            throw new InvalidPlayersException(player);
        }
    }

    public static Player from(String player) {
        return new Player(player);
    }

    public int length() {
        return player.length();
    }

    public String name() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player1 = (Player) o;
        return Objects.equals(player, player1.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}
