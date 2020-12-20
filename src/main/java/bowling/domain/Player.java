package bowling.domain;

import java.util.Objects;

public class Player {

    public static final int NAME_LENGTH = 3;
    private final String name;

    public Player(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("Name size must be 3");
        }

        this.name = name;
    }

    public String getName() {
        return name;
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
