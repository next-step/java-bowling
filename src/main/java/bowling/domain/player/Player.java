package bowling.domain.player;

import java.util.Objects;

public class Player {

    private static final String NAME_BASE = "|  %s ";

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String display() {
        return String.format(NAME_BASE, this.name);
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
