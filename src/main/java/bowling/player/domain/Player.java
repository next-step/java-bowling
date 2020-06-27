package bowling.player.domain;

import bowling.player.vo.Name;

import java.util.Objects;

public class Player {
    private final Name name;

    public Player(final String name) {
        this.name = new Name(name);
    }

    public boolean isSameName(final String name) {
        return this.name.equals(new Name(name));
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
