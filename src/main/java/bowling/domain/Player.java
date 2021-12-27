package bowling.domain;

import java.util.Objects;

import bowling.engine.Name;

public class Player implements Name {
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name must not be null or empty.");
        }

        if (name.length() > MAXIMUM_LENGTH) {
            throw new IllegalArgumentException("the maximum length of name is 3");
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
