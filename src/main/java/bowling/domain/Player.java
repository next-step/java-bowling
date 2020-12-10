package bowling.domain;

import bowling.exception.PlayerNameLengthException;

import java.util.Objects;

public class Player {

    private final String name;

    private Player(final String name) {
        this.name = name;
    }

    public static Player of(final String name) {
        validNameLength(name);
        return new Player(name);
    }

    private static void validNameLength(String name) {
        if(name.length() > 3){
            throw new PlayerNameLengthException();
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
}
