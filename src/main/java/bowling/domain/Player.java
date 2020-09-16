package bowling.domain;

import java.util.Objects;

public class Player {
    private Name name;

    private Player(Name name) {
        this.name = name;
    }

    public static Player from(String nameValue) {
        Name name = Name.from(nameValue);

        return new Player(name);
    }

    public String getName() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
