package bowling.model;

import java.util.Objects;

public class Player {

    static final int MAX_LENGTH_OF_NAME = 3;
    private String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        if (MAX_LENGTH_OF_NAME < name.trim().length()) {
            throw new PlayerNameLengthException();
        }
        return new Player(name);
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

    @Override
    public String toString() {
        return name;
    }
}